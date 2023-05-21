package org.lincks.maximilian.classmap;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClassMap {
    private final Map<Type,Object> map = new HashMap<>();

    public <T> T put(Class<T> clazz, T value){
        return (T) map.put(clazz,value);
    }

    public <T> T put(Type clazz, T value){
        return (T) map.put(clazz,value);
    }

    public <T> T get(Type clazz){
        return (T) map.get(clazz);
    }

    public int size(){
        return map.size();
    }

    public boolean isEmpty(){
        return map.isEmpty();
    }

    public boolean containsKey(Type clazz){
        return map.containsKey(clazz);
    }
}