package com.study.distributedLock;

import com.api.common.util.RedisService;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DistributedLock
 * @Description
 * @Author
 * @Date 2021/7/14 18:42
 * @Version 1.0
 **/
@RestController
@RequestMapping("/lock")
public class DistributedLock {

    @Autowired
    private RedissonClient redissonClient;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/lock")
    public String deductStock1(){
//        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
        int stock = Integer.parseInt(redisService.get("stock"));
        System.out.println("stock:"+stock);
        if(stock>0){
            int realStock = stock -1;
//            stringRedisTemplate.opsForValue().set("stock",realStock + "");
            redisService.set("stock",realStock + "");
            System.out.println("扣减库存为："+realStock);
        }else{
            System.out.println("库存不足");
        }
        return "end";
    }

    @GetMapping("/synchronized")
    public String deductStock2(){
        synchronized (this){
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock = stock -1;
                stringRedisTemplate.opsForValue().set("stock",realStock + "");
                System.out.println("扣减库存为："+realStock);
            }else{
                System.out.println("库存不足");
            }
        }
        return "end";
    }

    //Redis 使用 setNx
    @GetMapping("/setNx")
    public String deductStock3(){
        String lockkey = "lockkey";

        //给锁加上标记，防止高并发其他线程释放其他的锁
        String uuid = UUID.randomUUID().toString();
        try {
            //使用jedis.setnx(k,v); 当key存在，则set失败
//            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey,uuid);
            //设置线程生命周期，缺点是，线程执行时间过长还未执行完，锁已完成释放
//            stringRedisTemplate.expire(lockkey,10, TimeUnit.SECONDS);

            //遵从原子性，防止set成功，还未加超时时间宕机；
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey,uuid,10, TimeUnit.SECONDS);
            if(!result){
                return "false";
            }

            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock = stock -1;
//                stringRedisTemplate.opsForValue().set("stock",realStock + "");
                redisService.set("stock",realStock + "");
                System.out.println("扣减库存为："+realStock);
            }else{
                System.out.println("库存不足");
            }
        }finally {
            if (stringRedisTemplate.opsForValue().get(lockkey).equals(uuid)){
                //此种场景，缺点是在此处时间if中超时，线程2又加锁成功，此时在解锁，通过锁名解锁，会将线程2中得锁解锁；
                stringRedisTemplate.delete(lockkey);
            }
        }

        return "end";
    }

    //Redisson
    //redisson 解决了上述setNx，解锁其他得线程得锁，增加逻辑是“锁续命”：分线程定时（定时时间小于超时时间）会检查主线程是否结束，若没有结束则重新设置超时时间；
    @GetMapping("/redisson")
    public String deductStock4(){
        String lockkey = "lockkey";
        SimpleDateFormat n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(n.format(new Date()));
        RLock lock = redissonClient.getLock(lockkey);
        try {
            //默认是30s,若加锁成功，redisson会另起线程，每十秒去判断锁是否存在，若存在则延长锁的时间30s;
//            lock.lock();
            //设置为60s 后自动释放锁
//            lock.lock(60, TimeUnit.SECONDS);

//            boolean flag = lock.tryLock();
            //30秒时间内处于等待中,30秒时间内如果线程A释放锁，会获取到锁并返回true，否则30秒过后会获取不到锁并返回false，去执行下面的逻辑
            boolean flag = lock.tryLock(30,TimeUnit.SECONDS);
//            sleep(60000);
//            System.out.println(n.format(new Date()));

            if (flag){
                int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
                if(stock>0){
                    int realStock = stock -1;
                    stringRedisTemplate.opsForValue().set("stock",realStock + "");
                    System.out.println("扣减库存为："+realStock);
                }else{
                    System.out.println("库存不足");
                }
                return "false";
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁前");
            lock.unlock();
            System.out.println("释放锁后");
        }
        return "end";
    }

    //Redisson 读写操作，实现读写分离
    @GetMapping(value = "/rReadWriteLock")
    public String rReadWriteLock(String readWriteFlag){
        System.out.println(readWriteFlag);

        String lockkey = "read_write_lockkey";
        SimpleDateFormat n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RReadWriteLock rReadWriteLock = redissonClient.getReadWriteLock(lockkey);
        //写锁
        RLock read = rReadWriteLock.readLock();
        //读锁
        RLock write = rReadWriteLock.writeLock();

        try {
            switch (readWriteFlag){
                case "read":
                    System.out.println("=============封装处理读业务逻辑=============");
                    read.lock();
                    int readStock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
                    System.out.println("库存为："+readStock);
                    break;
                case "write":
                    System.out.println("=============封装处理写业务逻辑=============");
                    int writeStock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
                    write.lock();
                    int realStock = writeStock -1;
                    stringRedisTemplate.opsForValue().set("stock",realStock + "");
                    System.out.println("扣减库存为："+realStock);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.unlock();
            write.unlock();
        }
        return "end";
    }
}
