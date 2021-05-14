package org.adorsys.adssi.store.redis;

import org.adorsys.adssi.store.api.StringStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisStoreConfig {

    @Bean
    StringStore redisStringOperations(final ReactiveRedisConnectionFactory factory) {
        final RedisSerializer<String> serializer = new StringRedisSerializer();
        final RedisSerializationContext<String, String> context =
                RedisSerializationContext.<String, String>newSerializationContext()
                .key(serializer)
                .value(serializer)
                .hashKey(serializer)
                .hashValue(serializer)
                .build();
        
        ReactiveRedisTemplate<String, String> reactiveRedisTemplate = new ReactiveRedisTemplate<String, String>(factory, context);
        reactiveRedisTemplate.getConnectionFactory().getReactiveConnection().ping();
        return new RedisStringStore(reactiveRedisTemplate);
    }
}