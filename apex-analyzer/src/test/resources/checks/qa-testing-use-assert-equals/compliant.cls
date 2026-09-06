@IsTest
class IsEqualGoodTest {
    @IsTest static void testName() {
        String expected = 'Hello';
        String actual = 'Hello';
        Assert.areEqual(expected, actual, 'should match');
    }

    @IsTest static void testCount() {
        Integer expected = 5;
        Integer actual = 2 + 3;
        Assert.areEqual(expected, actual, 'count mismatch');
    }
}
