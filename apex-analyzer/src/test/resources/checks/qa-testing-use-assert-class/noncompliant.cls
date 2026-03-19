@IsTest
class SystemAssertTest {
    @IsTest static void testAccount() {
        Account a = new Account(Name = 'Test');
        System.assert(a != null);
        System.assertEquals('Test', a.Name);
        System.assertNotEquals(null, a.Name);
    }

    @IsTest static void testCalculation() {
        Integer result = 2 + 3;
        System.assert(result > 0);
        System.assertEquals(5, result);
    }
}
