package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "AA")
public class SomeAA implements TestInterface<Boolean>{
    @Override
    public int getNumber() {
        return 1;
    }
}
