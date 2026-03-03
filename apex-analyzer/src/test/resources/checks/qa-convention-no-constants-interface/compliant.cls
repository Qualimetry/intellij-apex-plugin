public class AppConstants {
    public static final String STATUS_ACTIVE = 'Active';
    public static final String STATUS_INACTIVE = 'Inactive';
    public static final Integer MAX_RETRIES = 3;
    public static final Integer BATCH_SIZE = 200;

    private AppConstants() { }
}

public interface Processable {
    void process();
    void validate();
}
