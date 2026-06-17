@IsTest
public class UseRunasCompliant {

    @IsTest static void testAccess() {
        User testUser = [SELECT Id FROM User LIMIT 1];
        System.runAs(testUser) {
            Boolean accessible = Account.SObjectType.getDescribe().isAccessible();
            Assert.isTrue(accessible, 'Account should be accessible');
        }
    }

    static User getTestUser() {
        return [SELECT Id FROM User LIMIT 1];
    }
}
