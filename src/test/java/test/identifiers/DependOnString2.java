package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "2")
public class DependOnString2 {
    public TestInterface<String> t;
    public DependOnString2(TestInterface<String> t){
        this.t = t;
    }
}
