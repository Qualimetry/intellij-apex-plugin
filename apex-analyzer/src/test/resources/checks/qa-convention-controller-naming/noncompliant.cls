public class AccountPage {
    public PageReference save() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        return null;
    }

    public PageReference cancel() {
        return new PageReference('/home');
    }

    public String getTitle() {
        return 'Account';
    }
}
