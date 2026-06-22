public class InstanceofCatchClass {
    public void processException() {
        try {
            doWork();
        } catch (Exception e) {
            if (e instanceof DmlException) {
                handleDml((DmlException) e);
            } else if (e instanceof CalloutException) {
                handleCallout((CalloutException) e);
            } else {
                System.debug('Other: ' + e.getMessage());
            }
        }
    }

    public void anotherMethod() {
        try {
            callService();
        } catch (Exception ex) {
            if (ex instanceof QueryException) {
                retryQuery();
            }
        }
    }

    private void doWork() { }
    private void handleDml(DmlException e) { }
    private void handleCallout(CalloutException e) { }
    private void callService() { }
    private void retryQuery() { }
}
