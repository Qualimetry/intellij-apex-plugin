public class SafeResourceHandler {
    private Database.SaveResult[] results;

    public void processWithCleanup() {
        try {
            results = Database.insert(new List<Account>{ new Account(Name='Test') });
        } catch (DmlException e) {
            System.debug(LoggingLevel.ERROR, e.getMessage());
            throw e;
        } finally {
            cleanup();
        }
    }

    public void executeWithLogging() {
        try {
            callExternalService();
        } finally {
            System.debug('Operation completed');
            logCompletion();
        }
    }

    private void cleanup() {
        results = null;
    }

    private void callExternalService() { }
    private void logCompletion() { }
}
