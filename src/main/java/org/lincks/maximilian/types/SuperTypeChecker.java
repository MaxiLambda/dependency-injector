package org.lincks.maximilian.types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SuperTypeChecker {
    private SuperTypeChecker() {
    }

    public static List<Type> getGenericSuperTypes(Class<?> clazz) {
        List<Type> types = new ArrayList<>();
        for (Class<?> c = clazz; c.getSuperclass() != null; c = c.getSuperclass()) {
            types.add(c.getGenericSuperclass());
        }
        return types;
    }
}
