public class UnsafeEndpoint {
    public void call(String url) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(url);
        req.setMethod('GET');
        new Http().send(req);
    }

    @AuraEnabled
    public static String fetchData(String endpoint) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);
        req.setMethod('GET');
        HttpResponse res = new Http().send(req);
        return res.getBody();
    }
}
