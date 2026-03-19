public class UnusedLocalNonCompliant {
    private Integer total;

    public void calculate() {
        Integer unused = 42;
        total = 100;
    }

    public Integer getTotal() {
        return total;
    }
}
