//
//package com.example.demo.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//        // Create the Jackson serializer with JavaTimeModule
//        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());  // Register JavaTimeModule for LocalDate
//        serializer.setObjectMapper(objectMapper);
//
//        redisTemplate.setDefaultSerializer(serializer);  // Set the custom serializer
//        return redisTemplate;
//    }
//}


package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
//import io.lettuce.core.ssl.SslContextBuilder;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class)); // Customize serializer if necessary
        return redisTemplate;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        String redisUrl = "rational-mantis-10875.upstash.io";
        String password = "ASp7AAIjcDFjMTAxZjJhNGU3MTg0MjEzOTBkZDQ0MjhhNDUyYjQwNHAxMA";

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(redisUrl);
        configuration.setPort(6379);
        configuration.setPassword(password);

        // Enable SSL/TLS connection
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration);
        factory.setUseSsl(true);  // This enables SSL connection with Upstash

        return factory;
    }
}
