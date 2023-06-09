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
    private static final Logger log = LoggerFactory.getLogger(IoCContainer.class);
    private static List<Class<?>> injectables;

    private IoCContainer() {
    }

    public static void initialize(List<String> paths) {
        injectables = paths.stream()
                .map(Reflections::new)
                .map(reflection -> reflection.get(TypesAnnotated.with(Injectable.class).asClass()))
                .flatMap(Set::stream)
                .distinct()
                .toList();

        String injectablesAsString = String.join("\n", injectables
                .stream()
                .map(Class::toString)
                .toList());
        log.info(injectablesAsString);
    }

    public static <T> void bind(Type clazz, T value) {
        classMap.put(clazz, value);
    }

    public static <T> T resolve(Class<T> clazz) {
        return resolve(clazz, clazz.getAnnotation(Injectable.class).identifier());
    }

    public static <T> T resolve(Class<T> clazz, String identifier) {
        List<Class<T>> injects = findAllMatchingWithIdentifier(clazz, identifier);

        return resolveInternal(injects, clazz, identifier);
    }

    public static <T> T resolve(Type clazz) {
        return resolve(clazz, "");
    }

    public static <T> T resolve(Type clazz, String identifier) {
        List<Class<T>> injects = findAllMatchingWithIdentifier(clazz, identifier);

        return resolveInternal(injects, clazz, identifier);
    }

    private static <T> T resolveInternal(List<Class<T>> injects, Type clazz, String identifier) {
        if (classMap.containsKey(clazz)) return classMap.get(clazz);
        if (injects.isEmpty()) throw new NoMatchingInjectableFoundException(clazz);

        if (injects.size() > 1) throw new TooManyInjectablesFoundException(clazz, injects);

        T value;
        try {
            value = InjectClassFactory.createInstance(injects.get(0), identifier);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new InstanceCreationException(e);
        }

        bind(clazz, value);
        return value;
    }

    public static boolean isInjectable(Type clazz) {
        return !findAllMatchingIgnoringIdentifier(clazz).isEmpty();
    }

    private static <T> List<Class<T>> findAllMatchingWithIdentifier(Class<T> clazz, String identifier) {
        return injectables.stream()
                .filter(clazz::isAssignableFrom)
                .filter(c -> c.getAnnotation(Injectable.class)
                        .identifier()
                        .startsWith(identifier))
                .map(c -> ((Class<T>) c))
                .toList();
    }

    private static Predicate<Class<?>> isAssignableToPredicate(Type clazz) {
        return c ->
                c.equals(clazz) ||
                        List.of(c.getGenericInterfaces()).contains(clazz) ||
                        SuperTypeChecker.getGenericSuperTypes(c).contains(clazz);
    }

    private static <T> List<Class<T>> findAllMatchingIgnoringIdentifier(Type clazz) {
        return injectables.stream()
                .filter(isAssignableToPredicate(clazz))
                .map(c -> ((Class<T>) c))
                .toList();
    }

    private static <T> List<Class<T>> findAllMatchingWithIdentifier(Type clazz, String identifier) {

        return injectables.stream()
                .filter(isAssignableToPredicate(clazz))
                .filter(c -> c.getAnnotation(Injectable.class).identifier().startsWith(identifier))
                .map(c -> ((Class<T>) c))
                .toList();
    }

}
