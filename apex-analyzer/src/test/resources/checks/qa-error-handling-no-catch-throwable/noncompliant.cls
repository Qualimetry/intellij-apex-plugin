public class ThrowableCatcherClass {
    public void executeBatch() {
        try {
            Database.executeBatch(new MyBatch());
        } catch (Throwable t) {
            System.debug('Batch failed: ' + t.getMessage());
        }
    }

    public String processData(String input) {
        try {
            return transform(input);
        } catch (Throwable ex) {
            logError(ex);
            return null;
        }
    }

    public void runIntegration() {
        try {
            HttpResponse resp = callExternalService();
            parseResponse(resp);
        } catch (Throwable error) {
            sendAlert('Integration failed');
        }
    }

    private String transform(String s) { return s.toUpperCase(); }
    private void logError(Throwable ex) { System.debug(String.valueOf(ex)); }
    private HttpResponse callExternalService() { return null; }
    private void parseResponse(HttpResponse r) { }
    private void sendAlert(String msg) { }
}
