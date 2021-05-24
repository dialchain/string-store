package com.plooh.adssi.store.map;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.plooh.adssi.store.api.StringStore;


public class MapStringStore implements StringStore {
    
    private final Map<String, ValueHolder> map = new HashMap<>();

    @Override
    public String get(final String key) {
        final ValueHolder v = map.get(key);
        return v == null || v.expired(Instant.now()) ? null : v.getValue();
    }

    @Override
    public Boolean set(final String key, final String value, final Long expSec) {
        map.put(key, new ValueHolder(value, expSec));
        return true;
    }

    @Override
    public Boolean set(final String key, final String value) {
        map.put(key, new ValueHolder(value, null));
        return true;
    }

    @Override
    public Boolean hasKey(final String key) {
        // This is importtant to secure expiration logic.
        return get(key) != null;
    }

    @Override
    public Boolean delete(final String key) {
        return map.remove(key)!=null;
    }
    
    @Override
    public Set<String> keys(){
        return map.keySet();
    }
}