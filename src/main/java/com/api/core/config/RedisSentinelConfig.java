package com.api.core.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * @ClassName RedisSentinelConfig
 * @Description
 * @Author
 * @Date 2021/8/25 10:12
 * @Version 1.0
 **/
@Configuration
@EnableAutoConfiguration
@Slf4j
public class RedisSentinelConfig {

    @Value("#{'${spring.redis.sentinel.nodes}'.split(',')}")
    private List<String> nodes;


    @Value("#{'${spring.redis.timeout}'}")
    private int timeout;

    @Value("#{'${spring.redis.database}'}")
    private int database;

    @Value("#{'${spring.redis.jedis.pool.max-idle}'}")
    private String maxIdle;

    @Value("#{'${spring.redis.jedis.pool.min-idle}'}")
    private String minIdle;

    @Value("#{'${spring.redis.jedis.pool.max-active}'}")
    private String maxActive;

    @Value("#{'${spring.redis.jedis.pool.max-wait}'}")
    private String maxWait;

    @Value("#{'${spring.redis.sentinel.master}'}")
    private String master;

    /**
     * 哨兵模式自动装配
     * @return
     */
    @Bean
    public RedissonClient redissonSentinel() {
        Config config = new Config();
        List<String> newNodes = new ArrayList(nodes.size());
        nodes.forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[0]))
                .setMasterName(master)
                .setDatabase(database)
                .setReadMode(ReadMode.SLAVE)
                .setTimeout(timeout);
        return Redisson.create(config);
    }


    /**
     *  实现功能和 redissonSentinel() 类似
     * 以下可以使用 Jedis 操作 redis
     * Jedis 是 Redis 官方推荐的面向 Java 的操作 Redis 的客户端
     */
    @Bean
    @ConfigurationProperties(prefix="spring.redis")
    public JedisPoolConfig getRedisConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    @Bean
    public RedisSentinelConfiguration sentinelConfiguration(){
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //配置matser的名称
        redisSentinelConfiguration.master("mymaster");
        //配置redis的哨兵sentinel
        Set<RedisNode> redisNodeSet = new HashSet<>();
        nodes.forEach(x->{
            redisNodeSet.add(new RedisNode(x.split(":")[0],Integer.parseInt(x.split(":")[1])));
        });
        log.info("redisNodeSet -->"+redisNodeSet);
        //哨兵模式添加set
        redisSentinelConfiguration.setSentinels(redisNodeSet);
        return redisSentinelConfiguration;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig, RedisSentinelConfiguration sentinelConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(sentinelConfig,jedisPoolConfig);
        return jedisConnectionFactory;
    }
}