package org.lincks.maximilian.injector.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.identifiers.DependOnInteger3;
import test.identifiers.DependOnString2;

import java.util.List;

class IoCContainerTest {

    @BeforeEach
    void setUp() {
        IoCContainer.initialize(List.of("test"));
    }

    @Test
    void resolve() {
        Assertions.assertEquals(3,IoCContainer.resolve(DependOnInteger3.class).t.getNumber());
        Assertions.assertEquals(2,IoCContainer.resolve(DependOnString2.class,"2").t.getNumber());
    }
}