public class NoRedundantParensNonCompliant {

    private Integer value;
    private String label;

    public Integer getValue() {
        return value;
    }

    public Integer calculate(Integer factor) {
        return (value);
    }

    public String getLabel() {
        return label;
    }
}
