@IsTest
public class AccountServiceTest {
    @TestSetup
    static void setup() {
        insert new Account(Name = 'Test Account');
    }

    @IsTest
    static void testGetAccounts() {
        List<Account> accounts = AccountService.getAccounts();
        System.assertEquals(1, accounts.size());
    }
}
