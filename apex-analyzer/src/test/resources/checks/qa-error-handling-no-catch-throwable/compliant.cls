public class SafeErrorHandler {
    public void executeBatch() {
        try {
            Database.executeBatch(new MyBatch());
        } catch (AsyncException e) {
            System.debug('Batch failed: ' + e.getMessage());
        }
    }

    public String processData(String input) {
        try {
            return transform(input);
        } catch (Exception e) {
            logError(e);
            return null;
        }
    }

    public void runIntegration() {
        try {
            HttpResponse resp = callExternalService();
            parseResponse(resp);
        } catch (CalloutException ce) {
            sendAlert('Integration failed: ' + ce.getMessage());
        } catch (Exception e) {
            System.debug('Unexpected: ' + e.getMessage());
        }
    }

    private String transform(String s) { return s.toUpperCase(); }
    private void logError(Exception e) { System.debug(e.getMessage()); }
    private HttpResponse callExternalService() { return null; }
    private void parseResponse(HttpResponse r) { }
    private void sendAlert(String msg) { }
}
