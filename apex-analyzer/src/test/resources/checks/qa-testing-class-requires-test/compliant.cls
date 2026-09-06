@IsTest
class OrderServiceTest {
    @IsTest static void testCalculateTotal() {
        OrderItem__c item = new OrderItem__c(UnitPrice__c = 10, Quantity__c = 3);
        OrderService svc = new OrderService();
        Decimal total = svc.calculateTotal(new List<OrderItem__c>{ item });
        Assert.areEqual(30, total, 'Total should be 30');
    }

    @IsTest static void testProcessOrder() {
        Order__c order = new Order__c(Status__c = 'New');
        OrderService svc = new OrderService();
        svc.processOrder(order);
        Assert.areEqual('Processing', order.Status__c, 'Status should be Processing');
    }
}
