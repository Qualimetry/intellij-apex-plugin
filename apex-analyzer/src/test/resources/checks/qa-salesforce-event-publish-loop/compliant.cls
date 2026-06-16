public class OrderEventPublisherCompliant {
    private List<Order> orders;

    public OrderEventPublisherCompliant(List<Order> orders) {
        this.orders = orders;
    }

    public void publishOrderEvents() {
        List<Order_Event__e> events = new List<Order_Event__e>();
        for (Order o : orders) {
            Order_Event__e evt = new Order_Event__e();
            evt.OrderId__c = o.Id;
            events.add(evt);
        }
        EventBus.publish(events);
    }
}
