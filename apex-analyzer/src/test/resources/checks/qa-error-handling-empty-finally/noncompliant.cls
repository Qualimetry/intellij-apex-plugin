public class EmptyFinallyExample {
    public void processRecords() {
        try {
            doWork();
        } finally { }
    }

    public void executeTask() {
        try {
            callService();
        } catch (Exception e) {
            System.debug(e.getMessage());
        } finally { }
    }

    private void doWork() { }
    private void callService() { }
}
