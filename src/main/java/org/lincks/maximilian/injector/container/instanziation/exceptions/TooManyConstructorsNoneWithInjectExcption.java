package org.lincks.maximilian.injector.container.instanziation.exceptions;

public class TooManyConstructorsNoneWithInjectExcption extends RuntimeException {
    public TooManyConstructorsNoneWithInjectExcption(Class<?> clazz) {
        super("More than one Constructor but none annotated with @Inject found in %s".formatted(clazz));
    }
}
