@IsTest
class CorrectAssertTest {
    @IsTest static void testBooleanCorrect() {
        Boolean condition = true;
        Assert.isTrue(condition, 'Should be true');
        Assert.isFalse(!condition, 'Should not be false');
    }

    @IsTest static void testValueComparison() {
        String expected = 'hello';
        String actual = getResult();
        Assert.areEqual(expected, actual, 'Values should match');
    }

    private static String getResult() { return 'hello'; }
}
