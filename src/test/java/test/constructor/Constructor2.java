package test.constructor;

import org.lincks.maximilian.injector.annotations.Inject;
import org.lincks.maximilian.injector.annotations.Injectable;
import test.inject.InjectableClass;

@Injectable
public class Constructor2 {


    public Constructor2(InjectableClass c){
    }

    @Inject
    public Constructor2(){}

}
