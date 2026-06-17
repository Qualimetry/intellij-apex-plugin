public class UnreachableCompliant {

    public Integer calculate(Integer value) {
        if (value > 0) {
            return value * 2;
        }
        return 0;
    }

    public void process(String input) {
        if (input == null) {
            throw new IllegalArgumentException('Input required');
        }
        doWork(input);
    }

    private void doWork(String val) { }
}
