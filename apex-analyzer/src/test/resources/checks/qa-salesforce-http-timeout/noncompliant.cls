public class ExternalApiService {
    private String baseUrl;

    public ExternalApiService(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String fetchData(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(baseUrl + path);
        req.setMethod('GET');
        req.setHeader('Content-Type', 'application/json');
        Http http = new Http();
        HttpResponse res = http.send(req);
        return res.getBody();
    }
}
