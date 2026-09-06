public class OrderService {
    public Decimal calculateTotal(List<OrderItem__c> items) {
        Decimal total = 0;
        for (OrderItem__c item : items) {
            total += item.UnitPrice__c * item.Quantity__c;
        }
        return total;
    }

    public void processOrder(Order__c order) {
        order.Status__c = 'Processing';
        update order;
    }
}
