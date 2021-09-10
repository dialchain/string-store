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

    private static ExpiringMap<String, String> map = ExpiringMap.builder()
        .maxSize(DEFAULT_MAX_SIZE)
        .variableExpiration()
        .expirationPolicy(ExpirationPolicy.ACCESSED)
        .build();

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
        map.put(key, value, ExpirationPolicy.ACCESSED, DEFAULT_DURATION, TimeUnit.SECONDS);
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