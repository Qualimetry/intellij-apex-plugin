public class NonCompliantLoopHandler {
    Integer j = 0;

    public void processBatch(Integer n) {
        for (Integer i = 0; i < n; j++) {
            System.debug('i=' + i + ' j=' + j);
        }
    }

    public void anotherLoop(Integer max) {
        Integer k = 0;
        for (Integer x = 0; x < max; k += 1) {
            System.debug('x=' + x);
        }
    }
}
