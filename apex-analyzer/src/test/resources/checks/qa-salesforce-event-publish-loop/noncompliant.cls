public class OrderEventPublisher {
    private List<Order> orders;

    public OrderEventPublisher(List<Order> orders) {
        this.orders = orders;
    }

    public void publishOrderEvents() {
        for (Order o : orders) {
            Order_Event__e evt = new Order_Event__e();
            evt.OrderId__c = o.Id;
            EventBus.publish(evt);
        }
    }
}
