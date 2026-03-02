public class FocusedType {
    private String name;
    private Integer count;

    public FocusedType(String name) {
        this.name = name;
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public String getSummary() {
        return name + ': ' + count;
    }
}
