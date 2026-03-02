public class EmptyMethodNonCompliant {

    private String name;
    private Integer count;

    public void process(String input) {
        name = input;
        count = input.length();
    }

    void run() { }
}
