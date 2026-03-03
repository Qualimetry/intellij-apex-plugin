trigger ContactValidationCompliant on Contact (before insert) {
    for (Contact c : Trigger.new) {
        if (String.isBlank(c.Email)) {
            c.addError('Missing email for this contact.');
        }
        if (c.Phone == null) {
            c.Phone.addError('Phone is required.');
        }
    }
}
