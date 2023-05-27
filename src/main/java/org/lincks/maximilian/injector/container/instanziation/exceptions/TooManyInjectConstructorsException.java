package org.lincks.maximilian.injector.container.instanziation.exceptions;

public class TooManyInjectConstructorsException extends RuntimeException {
    public TooManyInjectConstructorsException(Class<?> s) {
        super("More than one Constructor annotated with @Inject found in %s".formatted(s));
    }
}
