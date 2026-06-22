public class OrderProcessor {
    public void processOrders(List<Order> orders) {
        Integer count = 0;
        if (count == 0) {
            System.debug('No orders');
        }

        Integer index = orders.size();
        while (index > 0) {
            index--;
        }

        if (orders.isEmpty()) {
            return;
        }
    }
}
