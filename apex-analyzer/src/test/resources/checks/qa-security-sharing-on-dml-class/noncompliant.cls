public class SharingDmlNonCompliant {
    private Account account;

    public void createAccount() {
        account = new Account(Name = 'Test');
        insert account;
    }

    public void removeAccount() {
        delete account;
    }
}
