public class NoSessionIdUsage {
    public String getUserIdentifier() {
        return UserInfo.getUserId();
    }

    public void callExternalApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:ExternalAPI/data');
        req.setMethod('GET');
        new Http().send(req);
    }
}
