public class UnusedDeclaredNonCompliant {

    private Integer total;

    public void process(Integer value) {
        total = value * 2;
    }

    void run() {
        Integer unused = 1;
        String message = 'Processing started';
        System.debug(message);
    }
}
