public class AccountLookup {
    public void clear() {
        String name = '';
        Integer count = 0;
    }

    public Account find(String search) {
        List<Account> accounts = [SELECT Id FROM Account WHERE Name = :search LIMIT 1];
        return accounts.isEmpty() ? new Account() : accounts[0];
    }
}
