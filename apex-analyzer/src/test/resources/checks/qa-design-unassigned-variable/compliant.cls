public class AssignedFirst {
    public void processOrder() {
        Integer quantity = 0;
        Decimal price = 0.0;
        String status = 'Pending';

        Decimal total = quantity * price;
        if (status == 'Active') {
            doWork();
        }
    }
}
