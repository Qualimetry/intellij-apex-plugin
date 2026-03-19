public class CompliantTryHandler {
    public void processRecords() {
        try {
            insert new Account(Name = 'Test');
        } catch (DmlException e) {
            System.debug('DML error: ' + e.getMessage());
        }
    }

    public void callExternalService() {
        try {
            HttpResponse resp = sendRequest();
            handleResponse(resp);
        } catch (CalloutException e) {
            logError(e);
        } finally {
            cleanupResources();
        }
    }

    HttpResponse sendRequest() { return null; }
    void handleResponse(HttpResponse r) { }
    void logError(Exception e) { }
    void cleanupResources() { }
}
