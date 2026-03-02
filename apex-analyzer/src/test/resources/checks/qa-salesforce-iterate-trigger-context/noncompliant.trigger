trigger AccountTrigger on Account (before insert) {
    Account first = (Account) Trigger.new[0];
    first.Description = 'First record';
    Account second = (Account) Trigger.new[1];
    second.Description = 'Second record';
}
