public class CompliantWhileHandler {
    public void processQueue() {
        List<String> queue = getItems();
        Integer idx = 0;
        while (idx < queue.size()) {
            processItem(queue.get(idx));
            idx++;
        }
    }

    public void pollUntilReady() {
        Boolean isReady = false;
        while (!isReady) {
            isReady = checkStatus();
        }
    }

    List<String> getItems() { return new List<String>(); }
    void processItem(String s) { }
    Boolean checkStatus() { return true; }
}
