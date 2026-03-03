@IsTest
class AreEqualOverIsTrueNonCompliant {
    @IsTest static void testComparison() {
        String a = 'Test';
        String b = 'Test';
        Assert.isTrue(a == b, 'should be equal');
    }

    @IsTest static void testNumeric() {
        Integer x = 10;
        Assert.isTrue(x == 10, 'should be 10');
    }
}
