public class UnconditionalIfCompliant {

    private Boolean flag;

    public void run(Boolean condition) {
        if (condition) {
            doWork();
        }
    }

    public void check(String value) {
        if (value != null) {
            process(value);
        }
    }

    void doWork() { }

    void process(String val) { }
}
