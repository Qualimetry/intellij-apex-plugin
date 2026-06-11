public class EmptyIfExample {
    public void checkAccount(Account acc) {
        if (acc != null) { }
        System.debug('checked');
    }

    public void processOrder(Boolean isValid) {
        if (isValid) {
        }
    }

    public void validateContact(Contact c) {
        if (c.Email != null) {
            sendEmail(c);
        }
        if (c.Phone != null) { }
    }

    private void sendEmail(Contact c) { }
}
