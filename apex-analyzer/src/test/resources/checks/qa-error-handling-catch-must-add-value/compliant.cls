public class CatchMustAddValueCompliant {
    public void logBeforeRethrow() {
        try {
            doWork();
        } catch (Exception e) {
            Logger.error('Operation failed', e);
            throw e;
        }
    }

    public void debugAndRecover() {
        try {
            callApi();
        } catch (CalloutException e) {
            System.debug('Callout error: ' + e.getMessage());
        }
    }

    public void addErrorOnRecord(Account acc) {
        try {
            update acc;
        } catch (DmlException e) {
            acc.addError('Save failed: ' + e.getMessage());
        }
    }

    void doWork() { }
    void callApi() { }
}
