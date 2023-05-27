package org.lincks.maximilian.injector.container.exceptions;

public class InstanceCreationException extends RuntimeException {
    public InstanceCreationException(Throwable t) {
        super(t);
    }
}
