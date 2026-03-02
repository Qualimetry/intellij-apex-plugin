public class ChainedJobProcessor implements Queueable {
    private Integer step;

    public ChainedJobProcessor(Integer step) {
        this.step = step;
    }

    public void execute(QueueableContext ctx) {
        if (step == 1) {
            System.enqueueJob(new ChainedJobProcessor(2));
        } else if (step == 2) {
            System.enqueueJob(new ChainedJobProcessor(3));
        } else {
            System.enqueueJob(new ChainedJobProcessor(4));
        }
    }
}
