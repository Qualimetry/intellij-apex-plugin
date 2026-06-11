public class OrderProcessor {

    private List<Order> orders;

    public OrderProcessor() {
        this.orders = new List<Order>();
    }

    // Activates all pending orders in the queue
    public void processOrders() {
        for (Order o : orders) {
            o.Status = 'Activated';
        }
    }
}
