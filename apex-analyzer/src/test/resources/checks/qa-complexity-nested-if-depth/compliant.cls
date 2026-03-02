public class ValidationService {

    private Boolean validateAll;

    public void validate(Boolean a, Boolean b, Boolean c) {
        if (a) return;
        if (b) return;
        if (c) {
            doWork();
        }
    }

    private void doWork() {
        System.debug('Validation passed');
    }
}
