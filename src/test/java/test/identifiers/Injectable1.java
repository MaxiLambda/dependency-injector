package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "1")
public class Injectable1 implements TestInterface<String> {
    @Override
    public int getNumber() {
        return 1;
    }
}
