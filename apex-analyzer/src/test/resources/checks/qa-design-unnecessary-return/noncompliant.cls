public class EventHandler {

    public void handleEvent(Event__e evt) {
        if (evt == null) {
            return;
        }
        System.debug('Processing event: ' + evt.Type__c);
    }

    public void processRecords(List<SObject> records) {
        for (SObject rec : records) {
            if (rec.get('Status') == 'Inactive') {
                return;
            }
            rec.put('Processed__c', true);
        }
        return;
    }

    public void cleanup() {
        System.debug('Cleaning up resources');
        return;
    }
}
