public class ConfigManager {
    static final Integer RETRY_COUNT = 3;
    static final String DEFAULT_ENDPOINT = 'https://api.example.com';

    private Integer currentRetryCount;
    private String currentEndpoint;

    public void initialize() {
        currentRetryCount = RETRY_COUNT;
        currentEndpoint = DEFAULT_ENDPOINT;
    }
}
