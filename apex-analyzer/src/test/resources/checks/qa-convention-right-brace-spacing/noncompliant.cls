public class OrderService {
    public void process(Boolean active) {
        if (active) {
            doWork();
        }else {
            doNothing();
        }
    }

    public void validate(Boolean valid) {
        if (valid) {
            accept();
        }else {
            reject();
        }
    }
}
