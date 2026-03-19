public class UnusedParamCompliant {

    private Integer total;

    public void calculate(Integer factor) {
        total = factor * 10;
    }

    void run(Integer value) {
        doSomething(value);
    }

    void doSomething(Integer x) {
        total = x + 1;
    }
}
