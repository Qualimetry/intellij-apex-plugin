public class ConstantNamingNonCompliant {
    public static final String DEFAULT_NAME = 'Account';
    public static final Integer maxCount = 10;
    private Integer current;

    public void process() {
        current = maxCount;
    }

    public String getDefaultName() {
        return DEFAULT_NAME;
    }
}
