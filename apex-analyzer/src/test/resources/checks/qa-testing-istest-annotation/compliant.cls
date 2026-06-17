@IsTest
public class IstestAnnotationCompliant {

    private Integer count;

    public void setup() {
        count = 0;
    }

    @IsTest static void testCounter() {
        IstestAnnotationCompliant obj = new IstestAnnotationCompliant();
        obj.setup();
        Assert.areEqual(0, obj.count, 'Count should be zero');
    }
}
