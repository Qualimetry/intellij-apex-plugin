public final class OrderProcessor {

    private Decimal total;

    public OrderProcessor() {
        this.total = 0;
    }

    public final String getStatus() {
        return 'Active';
    }

    public void calculateTotal(List<OrderItem__c> items) {
        for (OrderItem__c item : items) {
            total += item.Price__c;
        }
    }

    final static Boolean isEnabled() {
        return true;
    }

    public final void reset() {
        this.total = 0;
    }
}
