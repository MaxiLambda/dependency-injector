package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "A.A.B.C")
public class SomeAABC implements TestInterface<Boolean>{
    @Override
    public int getNumber() {
        return 1;
    }
}
