public class SafeController {
    public List<Account> accounts;

    public SafeController() {
        accounts = [SELECT Id, Name FROM Account LIMIT 10];
    }

    public PageReference saveAccount() {
        Account a = new Account(Name = 'New');
        insert a;
        return new PageReference('/' + a.Id);
    }
}
