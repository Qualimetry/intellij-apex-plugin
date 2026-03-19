public class ShortMethods {
    public void processAccount(Account a) {
        validateAccount(a);
        enrichAccount(a);
        update a;
    }

    private void validateAccount(Account a) {
        if (String.isBlank(a.Name)) {
            throw new IllegalArgumentException('Name required');
        }
    }

    private void enrichAccount(Account a) {
        a.Description = 'Processed on ' + Date.today();
    }
}
