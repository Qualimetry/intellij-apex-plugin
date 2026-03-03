public class UnsafeRedirect {
    public PageReference goToPage(String recordId, String name) {
        return new PageReference('/apex/Detail?id=' + recordId + '&name=' + name);
    }

    public String buildUrl(String query) {
        return 'https://api.example.com/search?q=' + query;
    }
}
