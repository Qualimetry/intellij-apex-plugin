public class SafeLogging {
    public void processOrder(Order__c order) {
        Logger.info('Processing order', order.Id);
    }

    public void handleLogin(String userId) {
        Logger.info('User login', userId);
    }

    public void calculateTotal(List<LineItem__c> items) {
        Decimal total = 0;
        for (LineItem__c item : items) {
            total += item.Amount__c;
        }
        Logger.info('Total calculated', String.valueOf(total));
    }
}
