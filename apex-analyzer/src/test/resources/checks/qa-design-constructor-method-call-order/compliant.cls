public class OrderProcessor {
    private Integer maxRetries;
    private String endpoint;

    public OrderProcessor() {
        maxRetries = 3;
        endpoint = 'https://api.example.com';
    }

    public void configure() {
        System.debug('max=' + maxRetries);
    }
}
