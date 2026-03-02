public class OrderProcessor {

    private List<Order> orders;

    public OrderProcessor() {
        this.orders = new List<Order>();
    }

    public void processOrders() {
        // x = doSomething();
        for (Order o : orders) {
            o.Status = 'Activated';
        }
    }
}
