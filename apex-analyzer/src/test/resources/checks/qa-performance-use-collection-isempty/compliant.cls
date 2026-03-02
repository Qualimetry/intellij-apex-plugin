public class IsEmptyCheck {
    public void processAccounts(List<Account> accounts) {
        if (accounts.isEmpty()) {
            return;
        }
        if (!accounts.isEmpty()) {
            update accounts;
        }
    }

    public Boolean hasContacts(List<Contact> contacts) {
        return !contacts.isEmpty();
    }
}
