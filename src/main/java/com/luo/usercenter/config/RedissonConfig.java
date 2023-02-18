package com.luo.usercenter.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson配置
 *
 * @author lkx
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedissonConfig {

    private String host;
    private String port;
    @Bean
    public RedissonClient redissonClient() {
        // 1.创建配置
        Config config = new Config();
        // 2.添加redis地址
        String redisAddress = String.format("redis://%s:%s", host, port);
        config.useSingleServer().setAddress(redisAddress).setDatabase(3);
        // 3. Create Redisson instance 创建Redisson实例
        RedissonClient redisson = Redisson.create(config);
        RMap<Object, Object> myMap = redisson.getMap("myMap");
        return redisson;
    }
}
