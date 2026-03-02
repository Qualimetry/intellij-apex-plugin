public class Config {
    private final Integer MAX_RETRIES = 3;
    private final String ENDPOINT = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
