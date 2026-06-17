public class NoRethrowCompliant {
    public void logAndWrap() {
        try {
            doWork();
        } catch (Exception e) {
            System.debug(e.getMessage());
            throw new CustomException(e);
        }
    }

    public void logOnly() {
        try {
            callApi();
        } catch (CalloutException e) {
            Logger.error('Callout failed', e);
        }
    }

    public void recoverGracefully() {
        try {
            processRecords();
        } catch (DmlException e) {
            handleDmlError(e);
        }
    }

    void doWork() { }
    void callApi() { }
    void processRecords() { }
    void handleDmlError(DmlException e) { }
}
