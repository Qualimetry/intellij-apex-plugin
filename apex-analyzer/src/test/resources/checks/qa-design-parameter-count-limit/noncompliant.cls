public class ParamLimitNonCompliant {
    private String name;

    public void configure(Integer a, Integer b, Integer c, Integer d, Integer e, Integer f, Integer g, Integer h) {
        this.name = 'Configured';
    }

    public String getName() {
        return this.name;
    }
}
