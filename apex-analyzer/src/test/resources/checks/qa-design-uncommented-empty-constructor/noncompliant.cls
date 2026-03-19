public class NonCompliant {
    public NonCompliant() { }

    private String name;

    public void init() { }

    public NonCompliant(String name) {
        this.name = name;
    }

    public NonCompliant(Integer id) { }

    public void doWork() {
        System.debug('working');
    }
}
