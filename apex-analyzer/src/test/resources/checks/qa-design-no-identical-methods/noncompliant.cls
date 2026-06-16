public class NoIdenticalNonCompliant {
    private Integer x;
    private Integer y;

    public void resetX() {
        x = 1;
    }

    public void resetY() {
        x = 1;
    }

    public Integer getTotal() {
        return x + y;
    }
}
