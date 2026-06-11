public class OrderService {
    private final String apiEndpoint = 'https://api.example.com';
    private final Integer timeout = 30000;

    public String getEndpoint() {
        return apiEndpoint;
    }

    public Integer getTimeout() {
        return timeout;
    }
}
