package org.lincks.maximilian.injector.container.exceptions;

import java.lang.reflect.Type;
import java.util.List;

public class TooManyInjectablesFoundException extends RuntimeException {
    public <T> TooManyInjectablesFoundException(Type type, List<Class<T>> list){
        super("Too many Injectable Objects found for Type %s: %s".formatted(type, list));
    }

    public <T> TooManyInjectablesFoundException(Class<?> clazz, List<Class<T>> list){
        super("Too many Injectable Objects found for Class %s: %s".formatted(clazz, list));
    }
}
