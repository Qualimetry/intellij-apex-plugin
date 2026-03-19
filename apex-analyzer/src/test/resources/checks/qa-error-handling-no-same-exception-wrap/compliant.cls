public class NoSameExceptionWrapCompliant {
    public void wrapInDifferentType() {
        try {
            doWork();
        } catch (DmlException e) {
            throw new CustomException('Wrapped: ' + e.getMessage());
        }
    }

    public void logAndRethrow() {
        try {
            callApi();
        } catch (CalloutException e) {
            System.debug(e);
            throw e;
        }
    }

    public void recoverGracefully() {
        try {
            processRecords();
        } catch (QueryException e) {
            handleError(e);
        }
    }

    void doWork() { }
    void callApi() { }
    void processRecords() { }
    void handleError(QueryException e) { }
}
