public class ResourceCleaner {
    public void processData() {
        try {
            doWork();
        } catch (Exception e) {
            System.debug(e.getMessage());
        } finally {
            throw new CustomException('Cleanup failed');
        }
    }

    public void handleFile() {
        try {
            readFile();
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        } finally {
            throw new IllegalStateException('Cannot close');
        }
    }

    void doWork() { }
    void readFile() { }
}
