public class UsesNamedCredential {
    public HttpResponse callPartnerApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:PartnerAPI/v2/data');
        req.setMethod('GET');
        return new Http().send(req);
    }

    public HttpResponse callInternal() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:InternalSync/sync');
        req.setMethod('POST');
        return new Http().send(req);
    }
}
