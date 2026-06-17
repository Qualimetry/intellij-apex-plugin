public class OrderProcessor {
    private static final String STATUS_ACTIVE = 'Active';
    private static final Integer BATCH_THRESHOLD = 10;

    public void process(List<Order> orders) {
        String status = getStatus();
        if (status == STATUS_ACTIVE) {
            activate();
        }

        Integer count = orders.size();
        if (count > BATCH_THRESHOLD) {
            splitBatch();
        }
    }
}
