package com.plooh.adssi.store.map;

import com.plooh.adssi.store.api.StringStore;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

/**
 * ExpiringMap allows you to create a map that expires entries after a certain time period
 * or when a maximum map size has been exceeded.
 */
public class MapStringStore implements StringStore {

    private static final int DEFAULT_MAX_SIZE = 1000000;
    private static final int DEFAULT_DURATION = 1;

    private final ExpiringMap<String, String> map;
    private final int duration;

    public MapStringStore(final int maxSize, final int durationSec){
        if (maxSize <= 0){
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        if (durationSec <= 0){
            throw new IllegalArgumentException("duration (sec) must be > 0");
        }
        map = ExpiringMap.builder()
            .maxSize(maxSize)
            .variableExpiration()
            .expirationPolicy(ExpirationPolicy.ACCESSED)
            .build();
        duration = durationSec;
    }

    public MapStringStore(){
        this(DEFAULT_MAX_SIZE, DEFAULT_DURATION);
    }

    @Override
    public String get(final String key) {
        return map.get(key);
    }

    @Override
    public Boolean set(final String key, final String value, final Long expSec) {
        map.put(key, value, ExpirationPolicy.ACCESSED, expSec, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public Boolean set(final String key, final String value) {
        map.put(key, value, ExpirationPolicy.ACCESSED, duration, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public Boolean hasKey(final String key) {
        return map.containsKey(key);
    }

    @Override
    public Boolean delete(final String key) {
        return map.remove(key) != null;
    }
    
    @Override
    public Set<String> keys(){
        return map.keySet();
    }

}