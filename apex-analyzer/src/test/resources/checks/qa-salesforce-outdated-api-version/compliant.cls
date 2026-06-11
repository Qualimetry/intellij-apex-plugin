// API Version: 62.0
public class RecentApiClass {
    public void doWork() {
        List<Account> accounts = [SELECT Id, Name FROM Account LIMIT 10];
        for (Account a : accounts) {
            a.Description = 'Updated';
        }
        update accounts;
    }
}
