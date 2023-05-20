package org.lincks.maximilian.injector.container.exceptions;

import java.lang.reflect.Type;

public class TooManyInjectablesFoundException extends RuntimeException {
    public TooManyInjectablesFoundException(Type type){
        super("Too many Injectable Objects found for Type %s".formatted(type));
    }

    public TooManyInjectablesFoundException(Class<?> clazz){
        super("Too many Injectable Objects found for Class %s".formatted(clazz));
    }
}
