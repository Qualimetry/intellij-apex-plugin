public class ValidationService {

    private Boolean validateAll;

    public void validate(Boolean a, Boolean b, Boolean c, Boolean d) {
        if (a) {
            if (b) {
                if (c) {
                    if (d) {
                        doWork();
                    }
                }
            }
        }
    }

    private void doWork() {
        System.debug('Validation passed');
    }
}
