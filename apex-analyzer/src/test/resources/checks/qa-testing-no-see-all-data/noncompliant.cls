@IsTest
public class AccountServiceTest {
    @IsTest(seeAllData=true)
    static void testGetAccounts() {
        List<Account> accounts = [SELECT Id FROM Account];
        System.assertNotEquals(0, accounts.size());
    }

    @IsTest
    static void testCreateAccount() {
        Account a = new Account(Name = 'Test');
        insert a;
        System.assertNotEquals(null, a.Id);
    }
}
