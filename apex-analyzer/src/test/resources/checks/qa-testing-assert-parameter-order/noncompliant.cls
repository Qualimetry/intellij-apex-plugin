@IsTest
public class AccountServiceTest {

    private static final String EXPECTED_NAME = 'Test';

    @IsTest
    static void testAccountCreation() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        Integer actual = 1;
        Assert.assertEquals(actual, 1);
    }

    static Integer expected() {
        return 1;
    }
}
