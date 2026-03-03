public class UnsafeResourceHandler {
    public void processWithCleanup() {
        try {
            doWork();
        } finally {
            throw new CustomException('Cleanup failed');
        }
    }

    public void executeWithRetry() {
        try {
            callService();
        } catch (Exception e) {
            System.debug(e.getMessage());
        } finally {
            if (hasError()) {
                throw new RetryException('Must retry');
            }
        }
    }

    private void doWork() { }
    private void callService() { }
    private Boolean hasError() { return false; }
}
