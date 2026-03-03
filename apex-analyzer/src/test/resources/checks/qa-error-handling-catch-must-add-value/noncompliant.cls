public class CatchMustAddValueNoncompliant {
    public void bareRethrow() {
        try {
            doWork();
        } catch (Exception e) {
            throw e;
        }
    }

    public void anotherBareRethrow() {
        try {
            callApi();
        } catch (CalloutException ex) {
            throw ex;
        }
    }

    void doWork() { }
    void callApi() { }
}
