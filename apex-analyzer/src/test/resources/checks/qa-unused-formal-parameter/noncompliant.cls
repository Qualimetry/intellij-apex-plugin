public class UnusedParamNonCompliant {

    private Integer total;

    public void calculate(Integer factor) {
        total = factor * 10;
    }

    void run(Integer used, Integer unused) {
        doSomething(used);
    }

    void doSomething(Integer x) {
        total = x + 1;
    }
}
