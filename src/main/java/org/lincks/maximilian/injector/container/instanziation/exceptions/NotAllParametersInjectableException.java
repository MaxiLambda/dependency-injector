package org.lincks.maximilian.injector.container.instanziation.exceptions;

public class NotAllParametersInjectableException extends RuntimeException{
    public NotAllParametersInjectableException(Class<?> clazz) {
        super("Not all Parameters are injectable in Constructor in %s".formatted(clazz));
    }
}
