public class SafeEndpoint {
    public void call() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:MyAPI/data');
        req.setMethod('GET');
        new Http().send(req);
    }

    @AuraEnabled
    public static String fetchData() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:TrustedPartner/v2/data');
        req.setMethod('GET');
        HttpResponse res = new Http().send(req);
        return res.getBody();
    }
}
