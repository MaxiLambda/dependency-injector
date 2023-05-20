package org.lincks.maximilian.injector.container.exceptions;

import java.lang.reflect.Type;

public class NoMatchingInjectableFoundException extends RuntimeException {
    public NoMatchingInjectableFoundException(Type type){
        super("No matching Injectable found for Type %s".formatted(type));
    }

    public NoMatchingInjectableFoundException(Class<?> clazz){
        super("No matching Injectable found for Class %s".formatted(clazz));
    }
}
