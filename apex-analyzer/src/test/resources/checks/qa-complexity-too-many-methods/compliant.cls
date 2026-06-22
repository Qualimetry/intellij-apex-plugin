public class FocusedClass {
    public void create(Account a) {
        insert a;
    }

    public Account findById(Id accountId) {
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }

    public void updateAccount(Account a) {
        update a;
    }

    public void deleteAccount(Account a) {
        delete a;
    }
}
