public class OrderHandler extends BaseHandler {

    private static final String STATUS = 'Active';

    public OrderHandler() {
        super();
    }

    public void handleOrder(Order__c order) {
        order.Status__c = STATUS;
        update order;
    }

    public static List<Order__c> getActiveOrders() {
        return [SELECT Id, Status__c FROM Order__c WHERE Status__c = 'Active'];
    }
}
