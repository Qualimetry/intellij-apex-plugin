public class SizeCheck {
    public void processAccounts(List<Account> accounts) {
        if (accounts.size() == 0) {
            return;
        }
        if (accounts.size() > 0) {
            update accounts;
        }
    }

    public Boolean hasContacts(List<Contact> contacts) {
        return contacts.size() != 0;
    }
}
