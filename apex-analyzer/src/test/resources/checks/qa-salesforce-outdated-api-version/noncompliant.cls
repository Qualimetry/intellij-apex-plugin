// API Version: 42.0
public class OldApiClass {
    public void doWork() {
        List<Account> accounts = [SELECT Id, Name FROM Account LIMIT 10];
        for (Account a : accounts) {
            a.Description = 'Updated';
        }
        update accounts;
    }
}
