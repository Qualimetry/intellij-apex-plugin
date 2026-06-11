public class EmptyMethodCompliant {

    private String name;
    private Integer count;

    public void process(String input) {
        name = input;
        count = input.length();
    }

    // Intentional no-op
    void run() { }
}
