public class EmptyCheck {
    public Boolean hasValue(String s) {
        return String.isNotBlank(s);
    }

    public void processName(String name) {
        if (String.isBlank(name)) {
            throw new IllegalArgumentException('Name required');
        }
    }

    public Boolean isPopulated(List<Account> accounts) {
        return !accounts.isEmpty();
    }
}
