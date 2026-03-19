@IsTest
class NoDmlTest {
    @IsTest static void testAccounts() {
        TestDataFactory.createTestData();
        Account a = [SELECT Id FROM Account LIMIT 1];
        Assert.isNotNull(a, 'Account should exist');
    }

    @IsTest static void testContacts() {
        TestDataFactory.createTestData();
        Contact c = [SELECT Id, LastName FROM Contact LIMIT 1];
        Assert.areEqual('Smith', c.LastName, 'Contact name mismatch');
    }
}
