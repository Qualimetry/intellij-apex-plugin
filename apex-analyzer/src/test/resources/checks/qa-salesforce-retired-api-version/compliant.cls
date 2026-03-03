// apiVersion = 58
public class NewApiClass {
    public void doWork() {
        List<Account> accounts = [SELECT Id FROM Account LIMIT 5];
        for (Account a : accounts) {
            a.Name = 'Migrated';
        }
        update accounts;
    }
}
