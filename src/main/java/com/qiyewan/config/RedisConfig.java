package com.qiyewan.config;

import com.qiyewan.dto.AuthDto;
import com.qiyewan.dto.SimpleProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;

/**
 * Created by lhzbxx on 2016/10/26.
 *
 * Redis配置
 */

@Configuration
public class RedisConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisTemplate<String, AuthDto> authRedisTemplate() {
        RedisTemplate<String, AuthDto> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(AuthDto.class));
        return template;
    }

    @Bean
    public RedisTemplate<String, List<SimpleProductDto>> productsRedisTemplate() {
        RedisTemplate<String, List<SimpleProductDto>> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(List.class));
        return template;
    }

}
