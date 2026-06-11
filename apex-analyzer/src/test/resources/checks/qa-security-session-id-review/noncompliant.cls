public class UsesSessionId {
    public String getSessionToken() {
        return UserInfo.getSessionId();
    }

    public void callExternalApi() {
        HttpRequest req = new HttpRequest();
        req.setEndpoint('https://api.example.com/data');
        req.setHeader('Authorization', 'Bearer ' + UserInfo.getSessionId());
        new Http().send(req);
    }
}
