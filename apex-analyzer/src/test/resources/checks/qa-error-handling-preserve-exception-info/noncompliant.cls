public class ExceptionSwallower {
    public void processAccount(Id accountId) {
        try {
            Account a = [SELECT Name FROM Account WHERE Id = :accountId];
            update a;
        } catch (DmlException e) {
            throw new ApplicationException('Update failed');
        }
    }

    public void callExternalService() {
        try {
            Http h = new Http();
            HttpResponse resp = h.send(buildRequest());
        } catch (CalloutException ex) {
            throw new IntegrationException('Service unavailable');
        }
    }

    public void batchProcess(List<Account> accounts) {
        try {
            update accounts;
        } catch (DmlException error) {
            throw new BatchException('Batch update failed');
        }
    }

    private HttpRequest buildRequest() { return new HttpRequest(); }
}
