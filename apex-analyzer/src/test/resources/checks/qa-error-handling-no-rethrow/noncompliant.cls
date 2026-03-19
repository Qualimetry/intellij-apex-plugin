public class NoRethrowNoncompliant {
    public void directRethrow() {
        try {
            doWork();
        } catch (Exception e) {
            throw e;
        }
    }

    public void anotherDirectRethrow() {
        try {
            callApi();
        } catch (CalloutException ex) {
            throw ex;
        }
    }

    void doWork() { }
    void callApi() { }
}
