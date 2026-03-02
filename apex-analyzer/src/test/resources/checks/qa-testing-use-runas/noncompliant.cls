@IsTest
public class UseRunasNonCompliant {

    @IsTest static void testAccountQuery() {
        Account acc = new Account(Name = 'Test');
        insert acc;
        Assert.isNotNull(acc.Id, 'Should be created');
    }

    @IsTest static void testAccess() {
        Account.SObjectType.getDescribe().isAccessible();
    }
}
