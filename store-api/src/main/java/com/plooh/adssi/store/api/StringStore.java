package com.plooh.adssi.store.api;

import java.util.Set;

public interface StringStore {
    String get(String key);
    Boolean set(String key, String value, Long expSec);
    Boolean set(String key, String value);
    Boolean hasKey(String key);
    Boolean delete(String key);
    public Set<String> keys();    
}