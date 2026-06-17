public class OrderService {
    public void process(Boolean active, String status) {
        if (active) doWork();

        if (status == null) return;

        if (active) {
            validate();
        }
    }

    private void doWork() { }
    private void validate() { }
}
