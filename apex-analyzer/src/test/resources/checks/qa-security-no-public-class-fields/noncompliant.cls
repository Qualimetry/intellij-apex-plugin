public class Exposed {
    public String apiKey;
    public String secretToken;
    public List<String> permissions;
    private String internalId;

    public void init(String key, String token) {
        this.apiKey = key;
        this.secretToken = token;
    }
}
