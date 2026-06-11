public class Encapsulated {
    private String apiKey;
    private String secretToken;
    private List<String> permissions;

    public String getApiKey() { return apiKey; }

    public void setApiKey(String key) {
        if (String.isBlank(key)) throw new IllegalArgumentException('Key required');
        this.apiKey = key;
    }

    public List<String> getPermissions() {
        return permissions.clone();
    }
}
