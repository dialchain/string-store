package com.plooh.adssi.store.map;

import java.time.Instant;

public class ValueHolder {
    final String value;
    final Instant expire;

    public ValueHolder(String value, Long expSec) {
        this.value = value;
        this.expire = expSec==null?null:Instant.now().plusSeconds(expSec);
    }

    public String getValue() {
        return this.value;
    }

    public Instant getExpire() {
        return this.expire;
    }

    public boolean expired(Instant now) {
        return expire != null && now != null && now.isAfter(expire);
    }
}