# Dependency Injector

Classes can be marked as injectable using the @Injectable annotation. The constructor of an injectable class must only
rely on other injectable parameters. If an annotated class has more than one constructor, you have to mark the
constructor, which should be used to create instances of the class, with @Inject.

```java
import org.lincks.maximilian.injector.annotations.Inject;

//a class with one constructor
@Injectable
public class Injectable {
    public Injectable(InjectableClass c) {
        //...
    }
}

//a class with more than one constructor
@Injectable
public class Injectable2 {
    public Injectable2(String s) {
        //...
    }
    @Inject
    public Injectable2() {
        //...
    }
}
```

You can get instances of injectable classes using the IoCContainer.resolve function.

```java
import org.lincks.maximilian.injector.container.IoCContainer;

public class App {
    public static void main(String[] args) {
        TestClass test = IoCContainer.resolve(TestClass.class);
        //...
    }
}
```

You can pass an identifier to the @Injectable annotation. This identifier will be used to determine which injectables
should be used to create instances of the injectable. In the example below Injectable2 instances are created with
SomeImplementation1 instances because the identifier of Injectable2 starts with the full identifier of SomeImplementation1.

```java
import org.lincks.maximilian.injector.annotations.Injectable;

public interface SomeInterface {
    //...
}

@Injectable(identifier = "my-test")
public class Injectable2 {

    public Injectable2(SomeInterface some) {
        //...
    }
}

@Injectable(identifier = "my")
public class SomeImplementation1 {
    //...
}

@Injectable(identifier = "some-other")
public class SomeImplementation2 {
    //...
}
```