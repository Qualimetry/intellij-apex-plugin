public class AssertsInProd {
    public void processOrder(Order__c order) {
        System.assert(order != null);
        System.assertEquals('Active', order.Status__c);
        order.Total__c = calculateTotal(order);
    }

    public Decimal calculateTotal(Order__c order) {
        System.assert(order.Items__r != null, 'Items required');
        Decimal total = 0;
        for (OrderItem__c item : order.Items__r) {
            total += item.Amount__c;
        }
        return total;
    }
}
