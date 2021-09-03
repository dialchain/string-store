package com.plooh.adssi.store.map;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapStringStoreTest {

    MapStringStore mapStringStore;

    @BeforeEach
    protected void beforeEach() {
        mapStringStore = new MapStringStore();
    }

    @Test
    public void shouldSetAndGetWithDefaultDuration() throws Exception {
        for (int i = 0; i < 5; i++)
            mapStringStore.set(String.valueOf(i), String.valueOf(i));

        for (int i = 0; i < 5; i++)
            assertEquals(String.valueOf(i), mapStringStore.get(String.valueOf(i)));

        Thread.sleep(1010);

        for (int i = 0; i < 5; i++)
            assertEquals(null, mapStringStore.get(String.valueOf(i)));
    }

    @Test
    public void shouldSetAndGet() throws Exception {
        for (int i = 0; i < 5; i++)
            mapStringStore.set(String.valueOf(i), String.valueOf(i), 2L);

        for (int i = 0; i < 5; i++)
            assertEquals(String.valueOf(i), mapStringStore.get(String.valueOf(i)));

        Thread.sleep(2010);

        for (int i = 0; i < 5; i++)
            assertEquals(null, mapStringStore.get(String.valueOf(i)));
    }

    @Test
    public void shouldHaveKeys() {
        for (int i = 0; i < 5; i++)
            mapStringStore.set(String.valueOf(i), String.valueOf(i), 2L);

        assertEquals(5, mapStringStore.keys().size());
    }

    @Test
    public void shouldSetAndCheckAndDelete() {
        mapStringStore.set("key", "value", 1l);

        assertEquals(true, mapStringStore.hasKey("key"));

        mapStringStore.delete("key");

        assertEquals(false, mapStringStore.hasKey("key"));
    }

}
