public class IntegrationService {
    private String endpoint;

    public IntegrationService() {
        this.endpoint = IntegrationSettings__c.getInstance().Host__c;
    }

    public HttpResponse callService(String path) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint + path);
        req.setMethod('GET');
        return new Http().send(req);
    }
}
