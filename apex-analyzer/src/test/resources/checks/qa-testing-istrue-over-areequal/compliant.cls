@IsTest
class IsTrueOverAreEqualCompliant {
    @IsTest static void testActive() {
        Boolean active = true;
        Assert.isTrue(active, 'should be active');
    }

    @IsTest static void testInactive() {
        Boolean inactive = false;
        Assert.isFalse(inactive, 'should be inactive');
    }
}
