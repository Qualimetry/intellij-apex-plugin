public class UnusedDeclaredCompliant {

    private Integer total;

    public void process(Integer value) {
        total = value * 2;
    }

    void run() {
        Integer result = total + 5;
        String message = 'Value: ' + result;
        System.debug(message);
    }
}
