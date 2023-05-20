package test.inject;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable
public class DependOnNonInjectable {
    public DependOnNonInjectable(NotInjectable n){

    }
}
