package com.api.common.util;

import com.api.modules.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Description
 * @Author
 * @Date 2021/8/24 17:03
 * @Version 1.0
 **/

@Service("redisService")
public class RedisService {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    //操作redis客户端
    private static Jedis jedis;

    @Autowired //操作字符串的template，StringRedisTemplate是RedisTemplate的一个子集
    private StringRedisTemplate stringRedisTemplate;

    @Autowired  // RedisTemplate，可以进行所有的操作
    private RedisTemplate<Object, Object> redisTemplate;


//    public Jedis getJedis(){
//        if (jedis == null){
//            jedis = (Jedis) jedisConnectionFactory.getConnection().getNativeConnection();
//            return jedis;
//        }
//        return jedis;
//    }

    public void set(String key, String value) {
//        this.getJedis().set(key,value);
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
//        return this.getJedis().get(key);
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Boolean setIfAbsent(String key, String uuid, long timeout, TimeUnit unit){
       return stringRedisTemplate.opsForValue().setIfAbsent(key, uuid,timeout,unit);
    }

    public Boolean delete(String key){
//        return this.getJedis().del(key);
        return stringRedisTemplate.delete(key);
    }

    public void set(Student s) {
        redisTemplate.opsForValue().set(s.getId(), s);
    }

    public Student getStudent(String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }
}
