public class Config {
    private static final Integer MAX_RETRIES = 3;
    private static final String ENDPOINT = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
