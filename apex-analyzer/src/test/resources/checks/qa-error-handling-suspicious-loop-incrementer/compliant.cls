public class LoopProcessor {
    public void processItems(List<String> items) {
        for (Integer i = 0; i < items.size(); i++) {
            System.debug(items[i]);
        }
    }

    public void countdown(Integer start) {
        for (Integer n = start; n > 0; n--) {
            doWork(n);
        }
    }

    public void stepThrough(Integer limit) {
        for (Integer idx = 0; idx < limit; idx += 2) {
            process(idx);
        }
    }

    void doWork(Integer n) { }
    void process(Integer n) { }
}
