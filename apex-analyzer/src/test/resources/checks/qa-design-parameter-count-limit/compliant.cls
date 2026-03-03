public class ParamLimitCompliant {
    private String name;
    private Integer count;

    public void initialize(Integer a, Integer b) {
        this.name = 'Default';
        this.count = a + b;
    }

    public String formatOutput(String prefix, String suffix) {
        return prefix + this.name + suffix;
    }
}
