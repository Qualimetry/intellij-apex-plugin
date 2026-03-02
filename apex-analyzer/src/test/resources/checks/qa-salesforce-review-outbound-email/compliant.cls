public class NoDirectEmail {
    public void notifyAdmin(String body) {
        Event__e evt = new Event__e(
            Type__c = 'AdminNotification',
            Body__c = body
        );
        EventBus.publish(evt);
    }

    public void logActivity(Contact c) {
        Task t = new Task(
            WhoId = c.Id,
            Subject = 'Welcome',
            Status = 'Completed'
        );
        insert t;
    }
}
