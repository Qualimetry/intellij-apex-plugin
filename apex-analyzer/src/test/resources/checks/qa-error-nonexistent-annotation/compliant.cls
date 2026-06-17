public class OrderServiceCompliant {
    private String status;

    public OrderServiceCompliant(String status) {
        this.status = status;
    }

    @AuraEnabled
    public static String getStatus() {
        return 'Active';
    }

    @IsTest
    static void testGetStatus() {
        System.assertEquals('Active', OrderServiceCompliant.getStatus());
    }
}
