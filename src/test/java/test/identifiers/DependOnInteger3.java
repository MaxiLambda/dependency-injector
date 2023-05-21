package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable
public class DependOnInteger3 {
    public TestInterface<Integer> t;
    public DependOnInteger3(TestInterface<Integer> t){
        this.t = t;
    }
}
