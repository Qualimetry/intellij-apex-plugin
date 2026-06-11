public class NoTestCheck {
    private IHttpClient httpClient;

    public NoTestCheck(IHttpClient client) {
        this.httpClient = client;
    }

    public void processData() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:ExternalAPI/data');
        req.setMethod('GET');
        httpClient.send(req);
    }

    public String getConfig() {
        return OrgSettings__c.getInstance().Config__c;
    }
}
