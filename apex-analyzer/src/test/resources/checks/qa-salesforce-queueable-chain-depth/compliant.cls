public class SingleJobProcessor implements Queueable {
    private List<SObject> records;

    public SingleJobProcessor(List<SObject> records) {
        this.records = records;
    }

    public void execute(QueueableContext ctx) {
        for (SObject r : records) {
            r.put('Description', 'Processed');
        }
        update records;
    }
}
