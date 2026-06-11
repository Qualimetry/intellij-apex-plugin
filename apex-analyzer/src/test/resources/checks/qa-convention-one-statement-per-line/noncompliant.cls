public class OneStmtNonCompliant {
    private Integer total;
    private String name;

    public void initialize() {
        Integer a = 1; Integer b = 2;
        total = a + b;
    }

    public String getDescription() {
        name = 'Result';
        return name;
    }
}
