trigger AccountTrigger on Account (after insert) {
    List<Account> techAccounts = new List<Account>();
    for (Account a : Trigger.new) {
        if (a.Industry == 'Tech') {
            techAccounts.add(a);
        }
    }
    if (!techAccounts.isEmpty()) {
        System.enqueueJob(new TechAccountQueueable(techAccounts));
    }
}
