public class ValidInitializer {
    private String name;
    private Integer count;

    {
        name = 'Default';
        count = 0;
    }

    public ValidInitializer() {
        init();
    }

    public ValidInitializer(String n) {
        this.name = n;
    }

    public void doWork() {
        List<String> items = new List<String>();
    }

    private void init() { }
}
