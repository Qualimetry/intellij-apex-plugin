public class Compliant {
    private String name;

    // Required for JSON deserialization
    public Compliant() { }

    public Compliant(String name) {
        this.name = name;
    }

    /* Framework requirement */
    public Compliant(Integer id) { }

    public void doWork() {
        System.debug('working');
    }
}
