public class HardcodedEndpoint {
    public HttpResponse callPartnerApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('https://api.partner.com/v2/data');
        req.setHeader('Authorization', 'Bearer sk_live_abc123xyz');
        req.setMethod('GET');
        return new Http().send(req);
    }

    public HttpResponse callInternal() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('https://internal-api.company.com/sync');
        req.setMethod('POST');
        return new Http().send(req);
    }
}
