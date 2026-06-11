@IsTest
class WrongAssertTest {
    @IsTest static void testBooleanMisuse() {
        Boolean result = true;
        Assert.areEqual(true, result);
        Assert.areEqual(false, result);
        Assert.areNotEqual(true, result);
        Assert.areNotEqual(false, result);
    }

    @IsTest static void testReversedArgs() {
        String actual = 'hello';
        Assert.areEqual(actual, 'hello');
    }
}
