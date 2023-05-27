package test.identifiers;


import org.lincks.maximilian.injector.annotations.Injectable;

@Injectable(identifier = "A.B")
class SomeAB implements TestInterface<Boolean>{
    @Override
    public int getNumber() {
        return 0;
    }
}
