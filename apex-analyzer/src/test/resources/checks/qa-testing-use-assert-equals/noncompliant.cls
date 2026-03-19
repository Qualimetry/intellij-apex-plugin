@IsTest
class IsEqualBadTest {
    @IsTest static void testName() {
        String expected = 'Hello';
        String actual = 'Hello';
        Assert.isTrue(expected == actual, 'should match');
    }

    @IsTest static void testCount() {
        Integer expected = 5;
        Integer actual = 2 + 3;
        Assert.isTrue(expected == actual, 'count mismatch');
    }
}
