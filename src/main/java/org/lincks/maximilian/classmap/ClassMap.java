package org.lincks.maximilian.classmap;

import java.util.HashMap;
import java.util.Map;

public class ClassMap {
    private final Map<Class<?>,Object> map = new HashMap<>();

    public <T> T put(Class<T> clazz, T value){
        return (T) map.put(clazz,value);
    }

    public <T> T get(Class<T> clazz){
        return (T) map.get(clazz);
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    public boolean containsKey(Class<?> clazz){
        return map.containsKey(clazz);
    }
}