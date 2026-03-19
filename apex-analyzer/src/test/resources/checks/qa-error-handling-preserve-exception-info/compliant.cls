public class ProperExceptionHandler {
    public void processAccount(Id accountId) {
        try {
            Account a = [SELECT Name FROM Account WHERE Id = :accountId];
            update a;
        } catch (DmlException e) {
            throw new ApplicationException('Update failed', e);
        }
    }

    public void callExternalService() {
        try {
            Http h = new Http();
            HttpResponse resp = h.send(buildRequest());
        } catch (CalloutException ex) {
            throw new IntegrationException(ex.getMessage());
        }
    }

    public void batchProcess(List<Account> accounts) {
        try {
            update accounts;
        } catch (DmlException error) {
            System.debug('Failed: ' + error.getMessage());
            throw error;
        }
    }

    public void safeWrap() {
        try {
            doWork();
        } catch (Exception e) {
            throw new WrapperException('Wrapped: ' + e.getMessage(), e);
        }
    }

    private HttpRequest buildRequest() { return new HttpRequest(); }
    private void doWork() { }
}
