trigger ContactValidation on Contact (before insert) {
    for (Contact c : Trigger.new) {
        if (String.isBlank(c.Email)) {
            String msg = 'Missing email for: ' + c.LastName;
            c.addError(msg, false);
        }
        if (c.Phone == null) {
            c.Phone.addError('Phone is required: ' + c.LastName, false);
        }
    }
}
