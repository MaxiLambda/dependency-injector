package org.lincks.maximilian.injector.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ElementType.CONSTRUCTOR})
public @interface Inject {
    String identifier() default "";
}
