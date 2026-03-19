public class UnreachableNonCompliant {
    Integer run() {
        return 1;
        doSomething();
    }
    void doVoid() {
        return;
        doSomething();
    }
    void doThrow() {
        throw new SomeException();
        doSomething();
    }
    void doSomething() { }
}
