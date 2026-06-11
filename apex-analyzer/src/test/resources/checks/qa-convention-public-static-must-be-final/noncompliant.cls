public class AppConfig {
    public static Integer maxRetries = 3;
    public static String endpoint = 'https://api.example.com';

    public void process() {
        for (Integer i = 0; i < maxRetries; i++) {
            callout(endpoint);
        }
    }

    private void callout(String url) { }
}
