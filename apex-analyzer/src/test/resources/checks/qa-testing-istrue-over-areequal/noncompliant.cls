@IsTest
class IsTrueOverAreEqualNonCompliant {
    @IsTest static void testActive() {
        Boolean active = true;
        Assert.areEqual(active, true, 'should be active');
    }

    @IsTest static void testInactive() {
        Boolean inactive = false;
        Assert.areEqual(inactive, false, 'should be inactive');
    }
}
