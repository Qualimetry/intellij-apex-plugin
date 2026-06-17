public class HasDebugClass {
    public void processOrder(Order__c order) {
        System.debug('Processing order: ' + order);
        System.debug('User session: ' + UserInfo.getSessionId());
        Decimal total = calculateTotal(order);
        System.debug('Total: ' + total);
        insert order;
    }

    private Decimal calculateTotal(Order__c order) {
        return 100;
    }
}
