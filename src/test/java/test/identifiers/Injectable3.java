package test.identifiers;

import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable
public class Injectable3 implements TestInterface<Integer>{
    @Override
    public int getNumber() {
        return 3;
    }
}
