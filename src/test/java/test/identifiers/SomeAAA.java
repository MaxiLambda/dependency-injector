package test.identifiers;


import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "AAA")
public
class SomeAAA {
    public TestInterface<Boolean> t;
    public SomeAAA(TestInterface<Boolean> t){
        this.t = t;
    }
}
