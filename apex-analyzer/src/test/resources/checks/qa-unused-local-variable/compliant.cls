public class UnusedLocalCompliant {
    private Integer total;

    public void calculate() {
        Integer factor = 5;
        Integer base = 10;
        total = factor * base;
    }

    public void process(Integer value) {
        Integer result = value * 2;
        doSomething(result);
    }

    private void doSomething(Integer i) {
    }
}
