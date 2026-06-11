public class LengthCheck {
    public Boolean hasValue(String s) {
        return s.length() > 0;
    }

    public void processName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException('Name required');
        }
    }

    public Boolean isPopulated(List<Account> accounts) {
        return accounts.size() > 0;
    }
}
