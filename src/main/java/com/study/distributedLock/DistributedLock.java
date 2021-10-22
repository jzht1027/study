package com.study.distributedLock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @Autowired
//    private Redisson redisson;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/lock")
//    public String deductStock1(){
//        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//        System.out.println("stock:"+stock);
//        if(stock>0){
//            int realStock = stock -1;
//            stringRedisTemplate.opsForValue().set("stock",realStock + "");
//            System.out.println("扣减库存为："+realStock);
//        }else{
//            System.out.println("库存不足");
//        }
//        return "end";
//    }
//
//    @GetMapping("/synchronized")
//    public String deductStock2(){
//        synchronized (this){
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//            if(stock>0){
//                int realStock = stock -1;
//                stringRedisTemplate.opsForValue().set("stock",realStock + "");
//                System.out.println("扣减库存为："+realStock);
//            }else{
//                System.out.println("库存不足");
//            }
//        }
//        return "end";
//    }
//
//    //Redis 使用 setNx
//    @GetMapping("/setNx")
//    public String deductStock3(){
//        String lockkey = "lockkey";
//
//        //给锁加上标记，防止高并发其他线程释放其他的锁
//        String uuid = UUID.randomUUID().toString();
//        try {
//            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey,uuid);
//            //设置线程生命周期，缺点是，线程执行时间过长还未执行完，锁已完成释放
//            stringRedisTemplate.expire(lockkey,10, TimeUnit.SECONDS);
//
//            if(!result){
//                return "false";
//            }
//
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//            if(stock>0){
//                int realStock = stock -1;
//                stringRedisTemplate.opsForValue().set("stock",realStock + "");
//                System.out.println("扣减库存为："+realStock);
//            }else{
//                System.out.println("库存不足");
//            }
//        }finally {
//            if (stringRedisTemplate.opsForValue().get(lockkey).equals(uuid)){
//                stringRedisTemplate.delete(lockkey);
//            }
//        }
//
//        return "end";
//    }
//
//    //Redisson
//    @GetMapping("/redisson")
//    public String deductStock4(){
//        String lockkey = "lockkey";
//        SimpleDateFormat n = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(n.format(new Date()));
//        RLock lock = redisson.getLock(lockkey);
//        try {
//            //默认是30s,若加锁成功，redisson会另起线程，每十秒去判断锁是否存在，若存在则延长锁的时间30s;
////            lock.lock();
//            //设置为60s 后自动释放锁
////            lock.lock(60, TimeUnit.SECONDS);
//
////            boolean flag = lock.tryLock();
//            //30秒时间内处于等待中,30秒时间内如果线程A释放锁，会获取到锁并返回true，否则30秒过后会获取不到锁并返回false，去执行下面的逻辑
//            boolean flag = lock.tryLock(30,TimeUnit.SECONDS);
////            sleep(60000);
////            System.out.println(n.format(new Date()));
//
//            while (flag){
//                int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
//                if(stock>0){
//                    int realStock = stock -1;
//                    stringRedisTemplate.opsForValue().set("stock",realStock + "");
//                    System.out.println("扣减库存为："+realStock);
//                }else{
//                    System.out.println("库存不足");
//                }
//                return "false";
//            }
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("释放锁前");
//            lock.unlock();
//            System.out.println("释放锁后");
//        }
//        return "end";
//    }
}
