public class NoAssertsInProd {
    public void processOrder(Order__c order) {
        if (order == null) throw new IllegalArgumentException('Order required');
        order.Total__c = calculateTotal(order);
    }

    public Decimal calculateTotal(Order__c order) {
        if (order.Items__r == null) throw new IllegalArgumentException('Items required');
        Decimal total = 0;
        for (OrderItem__c item : order.Items__r) {
            total += item.Amount__c;
        }
        return total;
    }
}
