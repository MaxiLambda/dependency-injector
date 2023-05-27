package test.identifiers;


import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "A.A")
public
class DependsOnAA {
    public TestInterface<Boolean> t;
    public DependsOnAA(TestInterface<Boolean> t){
        this.t = t;
    }
}
