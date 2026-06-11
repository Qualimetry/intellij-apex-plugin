@IsTest
private class AccountServiceTestCompliant {

    @TestSetup
    static void setupTestData() {
        insert new Account(Name = 'Test Account');
        insert new Contact(LastName = 'Test Contact');
    }

    @IsTest
    static void testAccountCreation() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertEquals(1, accounts.size(), 'Expected one test account');
    }
}
