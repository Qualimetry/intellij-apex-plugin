@IsTest
public class EmptyTestClass {
    private static Account createAccount() {
        return new Account(Name = 'Test');
    }

    private static Contact createContact(Id accountId) {
        return new Contact(LastName = 'Smith', AccountId = accountId);
    }
}
