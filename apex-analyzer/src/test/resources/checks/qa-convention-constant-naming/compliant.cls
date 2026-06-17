public class ConstantNamingCompliant {
    public static final Integer MAX_COUNT = 10;
    public static final String DEFAULT_NAME = 'Account';
    private Integer current;

    public void process() {
        current = MAX_COUNT;
    }

    public String getDefaultName() {
        return DEFAULT_NAME;
    }
}
