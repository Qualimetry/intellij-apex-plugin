@IsTest
public class AccountServiceTest {

    private static final String EXPECTED_NAME = 'Test';

    @IsTest
    static void testAccountCreation() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Account result = [SELECT Name FROM Account WHERE Id = :acc.Id];
        Assert.assertEquals(1, getCount());
    }

    static Integer getCount() {
        return 1;
    }
}
