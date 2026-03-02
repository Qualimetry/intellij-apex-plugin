public class WhileBracesCompliant {

    private Boolean running;
    private Integer count;

    public void doWork() {
        count++;
    }

    public void process() {
        while (running) {
            doWork();
        }
    }

    public void reset() {
        count = 0;
        running = false;
    }
}
