package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "2")
public class Injectable2 implements TestInterface<String> {
    @Override
    public int getNumber() {
        return 2;
    }
}
