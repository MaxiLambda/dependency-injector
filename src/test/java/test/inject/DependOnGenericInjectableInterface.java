package test.inject;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable
public class DependOnGenericInjectableInterface {
    public DependOnGenericInjectableInterface(GenericInterface<Integer> c){

    }
}
