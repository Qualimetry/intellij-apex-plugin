public class NoUselessParensCompliant {

    private Integer value;
    private String label;

    public Integer getValue() {
        return value;
    }

    public Integer calculate(Integer factor) {
        return value * factor;
    }

    public String getLabel() {
        return label;
    }
}
