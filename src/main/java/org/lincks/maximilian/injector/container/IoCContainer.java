package org.lincks.maximilian.injector.container;


import org.lincks.maximilian.classmap.ClassMap;
import org.lincks.maximilian.injector.annotations.Injectable;
import org.lincks.maximilian.injector.container.exceptions.InstanceCreationException;
import org.lincks.maximilian.injector.container.exceptions.NoMatchingInjectableFoundException;
import org.lincks.maximilian.injector.container.exceptions.TooManyInjectablesFoundException;
import org.lincks.maximilian.injector.container.instanziation.InjectClassFactory;
import org.lincks.maximilian.types.SuperTypeChecker;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static org.reflections.scanners.Scanners.TypesAnnotated;


public class IoCContainer {

    private static final ClassMap classMap = new ClassMap();
    private static List<Class<?>> injectables;

    private static final Logger log = LoggerFactory.getLogger(IoCContainer.class);
    private IoCContainer() {
    }

    public static void initialize(List<String> paths) {
        injectables = paths.stream()
                .map(Reflections::new)
                .map(reflection -> reflection.get(TypesAnnotated.with(Injectable.class).asClass()))
                .flatMap(Set::stream)
                .distinct()
                .toList();

        String injectablesAsString = String.join("\n",injectables.stream().map(Class::toString).toList());
        log.info(injectablesAsString);
    }

    public static <T> void bind(Class<T> clazz, T value) {
        classMap.put(clazz, value);
    }

    public static <T> T resolve(Class<T> clazz) {
        if (classMap.containsKey(clazz)) return classMap.get(clazz);

        List<Class<T>> injects = findAllMatching(clazz);

        if (injects.isEmpty()) throw new NoMatchingInjectableFoundException(clazz);
        //TODO add Handling for classes with identifiers
        if (injects.size() > 1) throw new TooManyInjectablesFoundException(clazz);

        T value;
        try {
            value = InjectClassFactory.createInstance(injects.get(0));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new InstanceCreationException(e);
        }

        bind(clazz, value);
        return value;
    }

    public static <T> T resolve(Type clazz){
        List<Class<T>> injects = findAllMatching(clazz);

        if (injects.isEmpty()) throw new NoMatchingInjectableFoundException(clazz);
        //TODO add Handling for classes with identifiers
        if (injects.size() > 1) throw new TooManyInjectablesFoundException(clazz);

        return resolve(injects.get(0));
    }


    public static boolean isInjectable(Type clazz) {
        return !findAllMatching(clazz).isEmpty();
    }

    private static <T> List<Class<T>> findAllMatching(Class<T> clazz) {
        return injectables.stream()
                .filter(clazz::isAssignableFrom)
                .map(c -> ((Class<T>) c))
                .toList();
    }

    private static <T> List<Class<T>> findAllMatching(Type clazz) {
        Predicate<Class<?>> p = c ->
                c.equals(clazz) ||
                        List.of(c.getGenericInterfaces()).contains(clazz) ||
                        SuperTypeChecker.getGenericSuperTypes(c).contains(clazz);
        return injectables.stream()
                .filter(p)
                .map(c -> ((Class<T>) c))
                .toList();
    }

}
