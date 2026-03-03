public class UnconditionalIfNonCompliant {

    private Boolean flag;

    public void process() {
        if (flag) {
            doWork();
        }
    }

    void run() {
        if (true) { doSomething(); }
    }

    void doSomething() { }

    void doWork() { }
}
