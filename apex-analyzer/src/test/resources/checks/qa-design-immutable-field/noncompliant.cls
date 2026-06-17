public class OrderService {
    private String apiEndpoint = 'https://api.example.com';
    private Integer timeout = 30000;

    public String getEndpoint() {
        return apiEndpoint;
    }

    public Integer getTimeout() {
        return timeout;
    }
}
