public class ProperCatchHandler {
    public void processException() {
        try {
            doWork();
        } catch (DmlException e) {
            handleDml(e);
        } catch (CalloutException e) {
            handleCallout(e);
        } catch (Exception e) {
            System.debug('Unexpected: ' + e.getMessage());
        }
    }

    public Boolean isTypeMatch(Object obj) {
        if (obj instanceof Account) {
            return true;
        }
        return false;
    }

    public void anotherSafeMethod() {
        try {
            callService();
        } catch (QueryException e) {
            System.debug('Query failed: ' + e.getMessage());
        }
    }

    private void doWork() { }
    private void handleDml(DmlException e) { }
    private void handleCallout(CalloutException e) { }
    private void callService() { }
}
