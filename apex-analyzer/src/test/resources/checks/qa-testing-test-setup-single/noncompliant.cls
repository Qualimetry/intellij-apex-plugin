@IsTest
private class AccountServiceTest {

    @TestSetup
    static void setupAccounts() {
        insert new Account(Name = 'Test Account');
    }

    @TestSetup
    static void setupContacts() {
        insert new Contact(LastName = 'Test Contact');
    }

    @IsTest
    static void testAccountCreation() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertEquals(1, accounts.size(), 'Expected one test account');
    }
}
