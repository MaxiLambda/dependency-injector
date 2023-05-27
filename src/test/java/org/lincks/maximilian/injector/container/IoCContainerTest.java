package org.lincks.maximilian.injector.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.identifiers.*;

import java.util.List;

class IoCContainerTest {

    @BeforeEach
    void setUp() {
        IoCContainer.initialize(List.of("test"));
    }

    @Test
    void resolveGenericInterfaceWithWildcardIdentifier() {
        Assertions.assertEquals(Injectable3.class,IoCContainer.resolve(DependOnInteger3.class).t.getClass());
    }

    @Test
    void resolveGenericInterfaceWithCustomIdentifier() {
        Assertions.assertEquals(Injectable2.class,IoCContainer.resolve(DependOnString2.class,"2").t.getClass());
    }

    @Test
    void resolveGenericInterfaceWithSetIdentifier() {
        Assertions.assertEquals(SomeAABC.class, IoCContainer.resolve(DependsOnAA.class).t.getClass());
    }
}