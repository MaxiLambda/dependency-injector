package org.lincks.maximilian.injector.container.instanziation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lincks.maximilian.injector.container.IoCContainer;
import org.lincks.maximilian.injector.container.instanziation.exceptions.NotAllParametersInjectableException;
import test.constructor.Constructor2;
import test.constructor.DefaultConstructor;
import test.inject.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class InjectClassFactoryTest {

    @BeforeEach
    void setUp() {
        IoCContainer.initialize(List.of("test"));
    }

    @Test
    void getInjectConstructorSingle() throws NoSuchMethodException {
        Constructor<DefaultConstructor> defaultConstructor = DefaultConstructor.class
                .getConstructor(InjectableClass.class);
        Constructor<DefaultConstructor> receivedConstructor = InjectClassFactory
                .getInjectConstructor(DefaultConstructor.class);
        Assertions.assertEquals(defaultConstructor, receivedConstructor);
    }

    @Test
    void getInjectConstructorDouble() throws NoSuchMethodException {
        Constructor<Constructor2> defaultConstructor = Constructor2.class
                .getConstructor();
        Constructor<Constructor2> receivedConstructor = InjectClassFactory
                .getInjectConstructor(Constructor2.class);
        Assertions.assertEquals(defaultConstructor, receivedConstructor);
    }


    //Injectable Instance
    //Injectable depending on Injectable
    //Injectable depending on specific Version of generic Interface
    //Injectable Depending on not Injectable
    //NotInjectable

    @Test
    void createInstanceInjectable() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Assertions.assertEquals(
                InjectClassFactory.createInstance(InjectableClass.class).getClass(),
                InjectableClass.class);
    }

    @Test
    void createInstanceDependingOnInjectable() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Assertions.assertEquals(
                InjectClassFactory.createInstance(DependOnInjectable.class).getClass(),
                DependOnInjectable.class);
    }

    @Test
    void createInstanceDependingOnSpecifiedVersionOfGenericInterface() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Assertions.assertEquals(
                InjectClassFactory.createInstance(DependOnGenericInjectableInterface.class).getClass(),
                DependOnGenericInjectableInterface.class);
    }

    @Test
    void createInstanceDependingOnNotInjectable() {
        Assertions.assertThrows(NotAllParametersInjectableException.class,
                () -> InjectClassFactory.createInstance(DependOnNonInjectable.class));
    }
}