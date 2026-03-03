public class AsyncAccountProcessor {

    private static final Integer BATCH_SIZE = 200;

    @Future
    public static void processFuture(Id recordId) {
        Account acc = [SELECT Id, Name FROM Account WHERE Id = :recordId];
        acc.Description = 'Processed';
        update acc;
    }

    public void processAll(List<Id> recordIds) {
        for (Id recordId : recordIds) {
            processFuture(recordId);
        }
    }
}
