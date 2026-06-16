public class ResourceCleaner {
    public void processData() {
        try {
            doWork();
        } catch (Exception e) {
            throw new CustomException(e.getMessage(), e);
        } finally {
            cleanup();
        }
    }

    public void handleFile() {
        try {
            readFile();
        } finally {
            closeStream();
            System.debug('Resources released');
        }
    }

    void doWork() { }
    void readFile() { }
    void cleanup() { }
    void closeStream() { }
}
