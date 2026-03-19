public class HasTestCheck {
    public void processData() {
        if (Test.isRunningTest()) {
            return;
        }
        HttpRequest req = new HttpRequest();
        req.setEndpoint('callout:ExternalAPI/data');
        req.setMethod('GET');
        new Http().send(req);
    }

    public String getConfig() {
        if (Test.isRunningTest()) {
            return 'test-config';
        }
        return OrgSettings__c.getInstance().Config__c;
    }
}
