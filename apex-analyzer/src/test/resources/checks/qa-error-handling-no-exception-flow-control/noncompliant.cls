public class NonCompliantFlowControl {
    public void validateAccounts(List<Account> accounts) {
        for (Account a : accounts) {
            if (a.Name == null) { throw new ValidationException('Missing name'); }
        }
    }

    public void checkLimits(List<Integer> values) {
        Integer idx = 0;
        while (idx < values.size()) {
            if (values.get(idx) > 1000) { throw new LimitException('Over limit'); }
            idx++;
        }
    }
}
