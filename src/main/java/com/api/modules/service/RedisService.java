package com.api.modules.service;

import com.api.modules.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName RedisService
 * @Description
 * @Author
 * @Date 2021/8/24 17:03
 * @Version 1.0
 **/

@Service("redisService")
public class RedisService {
    @Autowired //操作字符串的template，StringRedisTemplate是RedisTemplate的一个子集
    private StringRedisTemplate stringRedisTemplate;

    @Autowired  // RedisTemplate，可以进行所有的操作
    private RedisTemplate<Object, Object> redisTemplate;

    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void set(Student s) {
        redisTemplate.opsForValue().set(s.getId(), s);
    }

    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Student getStudent(String key) {
        return (Student) redisTemplate.opsForValue().get(key);
    }
}
