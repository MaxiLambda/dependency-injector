package org.lincks.maximilian.injector.container.instanziation;

import org.lincks.maximilian.injector.annotations.Inject;
import org.lincks.maximilian.injector.container.IoCContainer;
import org.lincks.maximilian.injector.container.instanziation.exceptions.NotAllParametersInjectableException;
import org.lincks.maximilian.injector.container.instanziation.exceptions.TooManyConstructorsNoneWithInjectExcption;
import org.lincks.maximilian.injector.container.instanziation.exceptions.TooManyInjectConstructorsException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;

public class InjectClassFactory {

    private InjectClassFactory(){}

    public static <T> Constructor<T> getInjectConstructor(Class<T> clazz){
        List<Constructor<T>> constructors = List.of((Constructor<T>[]) clazz.getDeclaredConstructors());
        if (constructors.size() == 1) {
            return constructors.get(0);
        } else {
            List<Constructor<T>> annotatedConstructors = constructors.stream()
                    .filter(constructor -> constructor.isAnnotationPresent(Inject.class))
                    .toList();
            if(annotatedConstructors.size() > 1) throw
                    new TooManyInjectConstructorsException(clazz);
            if(annotatedConstructors.isEmpty()) throw
                    new TooManyConstructorsNoneWithInjectExcption(clazz);
            return annotatedConstructors.get(0);
        }
    }
    public static <T> T createInstance(Class<T> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        return createInstance(clazz, "");
    }
    public static <T> T createInstance(Class<T> clazz, String identifier) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<T> constructor = getInjectConstructor(clazz);

        List<Type> paramTypes = List.of(constructor.getGenericParameterTypes());

        boolean allParamsInjectable = paramTypes.stream()
                .allMatch(IoCContainer::isInjectable);

        if(!allParamsInjectable) throw new NotAllParametersInjectableException(clazz);

        Object[] params = paramTypes.stream()
                .map(type -> IoCContainer.resolve(type,identifier))
                .toArray();
        return constructor.newInstance(params);
    }
}
