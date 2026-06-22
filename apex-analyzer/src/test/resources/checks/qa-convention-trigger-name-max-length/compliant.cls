trigger AccountValidation on Account (before insert, before update) {
    for (Account acc : Trigger.new) {
        if (acc.Name == null) {
            acc.Name = 'Default';
        }
    }
}
