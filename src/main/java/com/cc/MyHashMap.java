package com.cc;

import java.util.ArrayList;

public class MyHashMap<K, V> {
    private ArrayList<K> keys = new ArrayList<>();
    private ArrayList<V> values = new ArrayList<>();

    public MyHashMap() {
    }


    public ArrayList<K> keyset() {
        ArrayList<K> result = new ArrayList<>();
        for (K k : keys) {
            if (k != null) {
                result.add(k);
            }
        }
        return result;
    }

    public void put(K key, V value) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                values.set(i, value);
                return;
            }
        }
        keys.add(key);
        values.add(value);
    }

    public V get(K key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                return values.get(i);
            }
        }
        return null;
    }
}
