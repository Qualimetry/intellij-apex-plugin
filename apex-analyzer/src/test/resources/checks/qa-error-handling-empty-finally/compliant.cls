public class ProperFinallyHandler {
    private Boolean isLocked = false;

    public void processWithLock() {
        isLocked = true;
        try {
            performUpdate();
        } finally {
            isLocked = false;
        }
    }

    public void executeWithLogging() {
        try {
            callExternalApi();
        } finally {
            System.debug('Execution finished');
        }
    }

    public void batchProcess() {
        try {
            Database.executeBatch(new MyBatch());
        } catch (AsyncException e) {
            System.debug(LoggingLevel.ERROR, e.getMessage());
        } finally {
            notifyAdmin();
        }
    }

    private void performUpdate() { }
    private void callExternalApi() { }
    private void notifyAdmin() { }
}
