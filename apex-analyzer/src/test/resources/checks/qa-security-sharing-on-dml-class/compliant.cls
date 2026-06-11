public with sharing class SharingDmlCompliant {
    private Account account;

    public void createAccount() {
        account = new Account(Name = 'Test');
        insert account;
    }

    public void updateAccount() {
        account.Name = 'Updated';
        update account;
    }
}
