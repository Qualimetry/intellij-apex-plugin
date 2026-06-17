public class AppConfig {
    public static final Integer MAX_RETRIES = 3;
    public static final String ENDPOINT = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < MAX_RETRIES; i++) {
            callout(ENDPOINT);
        }
    }

    private void callout(String url) { }
}
