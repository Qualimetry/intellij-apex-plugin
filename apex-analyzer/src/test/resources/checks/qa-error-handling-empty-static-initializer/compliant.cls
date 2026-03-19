public class ValidStaticInit {
    public static final String DEFAULT_NAME;
    public static final Integer MAX_BATCH;

    static {
        DEFAULT_NAME = 'Default';
        MAX_BATCH = 200;
    }

    public static void process() {
        System.debug(DEFAULT_NAME);
    }

    public static Integer getMaxBatch() {
        return MAX_BATCH;
    }
}
