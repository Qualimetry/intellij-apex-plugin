trigger AccountTrigger on Account (after insert) {
    Database.executeBatch(new AccountCleanupBatch());
    for (Account a : Trigger.new) {
        if (a.Industry == 'Tech') {
            Database.executeBatch(new TechAccountBatch(a.Id));
        }
    }
}
