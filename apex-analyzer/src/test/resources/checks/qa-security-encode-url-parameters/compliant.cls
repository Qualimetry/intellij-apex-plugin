public class SafeRedirect {
    public PageReference goToPage(String recordId, String name) {
        String url = '/apex/Detail?id=' + EncodingUtil.urlEncode(recordId, 'UTF-8')
            + '&name=' + EncodingUtil.urlEncode(name, 'UTF-8');
        return new PageReference(url);
    }

    public String buildUrl(String query) {
        return 'https://api.example.com/search?q=' + EncodingUtil.urlEncode(query, 'UTF-8');
    }
}
