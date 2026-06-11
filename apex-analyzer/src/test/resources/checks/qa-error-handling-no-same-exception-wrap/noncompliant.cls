public class NoSameExceptionWrapNoncompliant {
    public void wrapDmlInDml() {
        try {
            doWork();
        } catch (DmlException e) {
            throw new DmlException(e.getMessage());
        }
    }

    public void wrapCalloutInCallout() {
        try {
            callApi();
        } catch (CalloutException e) {
            throw new CalloutException('Retry: ' + e.getMessage());
        }
    }

    void doWork() { }
    void callApi() { }
}
