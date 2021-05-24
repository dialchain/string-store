package com.plooh.adssi.store.redis;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

import com.plooh.adssi.store.api.StringStore;
import org.springframework.data.redis.core.ReactiveRedisOperations;

public class RedisStringStore implements StringStore {
    
    protected final ReactiveRedisOperations<String, String> redisStringTemplate;

    public RedisStringStore(ReactiveRedisOperations<String, String> redisStringTemplate) {
        this.redisStringTemplate = redisStringTemplate;
    }

    @Override
    public String get(String key) {
        return redisStringTemplate.opsForValue().get(key).block();
    }

    @Override
    public Boolean set(String key, String value, Long exp) {
        return redisStringTemplate.opsForValue().set(key, value, Duration.ofSeconds(exp)).block();    
    }

    @Override
    public Boolean set(String key, String value) {
        return redisStringTemplate.opsForValue().set(key, value).block();    
    }

    @Override
    public Boolean hasKey(String key){
        return redisStringTemplate.hasKey(key).block();
    }

    @Override
    public Boolean delete(String key) {
        return redisStringTemplate.opsForValue().delete(key).block();
    }

    @Override
    public Set<String> keys() {
        return redisStringTemplate.keys("*").collect(Collectors.toSet()).block();
    }
}