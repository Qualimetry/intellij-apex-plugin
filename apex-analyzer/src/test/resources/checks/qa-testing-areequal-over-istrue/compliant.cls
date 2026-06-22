@IsTest
class AreEqualOverIsTrueCompliant {
    @IsTest static void testComparison() {
        String a = 'Test';
        String b = 'Test';
        Assert.areEqual(a, b, 'should be equal');
    }

    @IsTest static void testNumeric() {
        Integer x = 10;
        Assert.areEqual(10, x, 'should be 10');
    }
}
