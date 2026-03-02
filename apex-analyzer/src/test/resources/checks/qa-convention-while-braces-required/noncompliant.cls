public class WhileBracesNonCompliant {

    private Boolean running;
    private Integer count;

    public void doWork() {
        count++;
    }

    public void process() {
        while (running) count++;
    }

    public void reset() {
        count = 0;
        running = false;
    }
}
