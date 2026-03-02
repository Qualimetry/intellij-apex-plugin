public class OrderProcessor {
    private Integer maxRetries;
    private String endpoint;

    public OrderProcessor() {
        configure();
        maxRetries = 3;
        endpoint = 'https://api.example.com';
    }

    private void configure() {
        System.debug('Configuring');
    }
}
