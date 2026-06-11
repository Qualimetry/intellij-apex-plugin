public class CalloutsBatch implements Database.Batchable<SObject>, Database.AllowsCallouts {
    public Database.QueryLocator start(Database.BatchableContext bc) {
        return Database.getQueryLocator('SELECT Id, Name FROM Account');
    }

    public void execute(Database.BatchableContext bc, List<Account> scope) {
        for (Account a : scope) {
            HttpRequest req = new HttpRequest();
            req.setEndpoint('callout:ExternalAPI/' + a.Id);
            new Http().send(req);
        }
    }

    public void finish(Database.BatchableContext bc) { }
}
