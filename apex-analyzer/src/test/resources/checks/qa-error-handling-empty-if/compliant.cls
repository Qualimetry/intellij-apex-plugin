public class ConditionalHandler {
    private Boolean isActive = true;

    public void processAccount(Account acc) {
        if (acc != null) {
            acc.Name = 'Updated';
            update acc;
        }
    }

    public String getStatus(Boolean condition) {
        if (condition) {
            return 'Active';
        } else {
            return 'Inactive';
        }
    }

    public void handleBulk(List<Contact> contacts) {
        if (!contacts.isEmpty()) {
            Database.insert(contacts);
        }
    }
}
