public class OrderProcessor {
    public void process(List<Order> orders) {
        String status = getStatus();
        if (status == 'Active') {
            activate();
        }

        Integer count = orders.size();
        if (count > 10) {
            splitBatch();
        }
    }
}
