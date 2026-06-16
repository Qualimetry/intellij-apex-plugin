public class ForBracesNonCompliant {
    private Integer total;

    public void calculate() {
        total = 0;
        for (Integer i = 0; i < 10; i++)
            total += i;
    }

    public Integer getTotal() {
        return total;
    }
}
