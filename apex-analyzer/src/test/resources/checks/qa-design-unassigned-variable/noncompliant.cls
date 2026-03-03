public class UnassignedUse {
    public void processOrder() {
        Integer quantity;
        Decimal price;
        String status;

        Decimal total = quantity * price;
        if (status == 'Active') {
            doWork();
        }
    }
}
