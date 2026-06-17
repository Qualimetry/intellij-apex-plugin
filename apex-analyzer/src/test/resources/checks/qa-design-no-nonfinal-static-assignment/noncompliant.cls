public class ConfigManager {
    static Integer retryCount;
    static String endpoint;

    public void initialize() {
        retryCount = 3;
        endpoint = 'https://api.example.com';
    }
}
