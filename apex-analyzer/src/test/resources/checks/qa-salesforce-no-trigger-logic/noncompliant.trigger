trigger AccountTrigger on Account (before insert, before update) {
    for (Account a : Trigger.new) {
        if (a.Name == null) {
            a.addError('Name is required');
        }
    }
    List<Account> parents = [SELECT Id FROM Account LIMIT 10];
    for (Account a : Trigger.new) {
        a.Description = 'Processed';
    }
    insert new Task(Subject = 'Follow up');
    update Trigger.new;
}
