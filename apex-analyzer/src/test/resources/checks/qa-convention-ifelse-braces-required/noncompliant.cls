public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        }
        else
            doNothing();

        if (active) {
            activate();
        }
        else
            deactivate();
    }
}
