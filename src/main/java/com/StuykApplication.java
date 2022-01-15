package com;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@MapperScan(basePackages="com.api.modules.dao") //开启注解扫描，指定扫描文件为com.api.modules.dao包底下的所有包含`@mapper`的类。
public class StuykApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuykApplication.class, args);
    }

//    //初始化，往Redisson 中初始化个redis 的连接
//    @Bean
//    public Redisson redisson()  {
//        //此为单机模式
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6383").setDatabase(0);
//        return (Redisson) Redisson.create(config);
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(StringRedisTemplate.class)
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory){
//        StringRedisTemplate template = new StringRedisTemplate();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
}
