public class IntegrationService {
    private String endpoint;

    public IntegrationService() {
        this.endpoint = '192.168.1.1';
    }

    public HttpResponse callService(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('http://' + endpoint + '/' + path);
        req.setMethod('GET');
        return new Http().send(req);
    }
}
