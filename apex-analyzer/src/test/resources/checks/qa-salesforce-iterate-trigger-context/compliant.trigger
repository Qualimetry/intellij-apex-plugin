trigger AccountTrigger on Account (before insert) {
    for (Account a : (List<Account>) Trigger.new) {
        if (String.isBlank(a.Description)) {
            a.Description = 'Processed';
        }
    }
}
