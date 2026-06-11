public class CompliantLoopHandler {
    public void processList(Integer n) {
        for (Integer i = 0; i < n; i++) {
            System.debug('Index: ' + i);
        }
    }

    public void doubleLoop(Integer rows, Integer cols) {
        for (Integer r = 0; r < rows; r++) {
            System.debug('Row: ' + r);
        }
        for (Integer c = 0; c < cols; c++) {
            System.debug('Col: ' + c);
        }
    }

    public void stepLoop(Integer max) {
        for (Integer x = 0; x < max; x += 2) {
            System.debug('Step: ' + x);
        }
    }
}
