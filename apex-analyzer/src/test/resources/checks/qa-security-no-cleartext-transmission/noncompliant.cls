public class InsecureEndpoint {
    public void login(String password) {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('http://api.example.com/login');
        req.setMethod('POST');
        req.setBody('{"password":"' + password + '"}');
        new Http().send(req);
    }

    public String fetchData() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('http://internal.corp.com/data');
        req.setMethod('GET');
        HttpResponse res = new Http().send(req);
        return res.getBody();
    }
}
