public class SafeController {
    private List<Account> cachedAccounts;
    private Integer contactCount;

    public SafeController() {
        cachedAccounts = [SELECT Id, Name FROM Account LIMIT 10];
        contactCount = [SELECT COUNT() FROM Contact];
    }

    public List<Account> fetchAccounts() {
        return cachedAccounts;
    }

    public Integer fetchContactCount() {
        return contactCount;
    }
}
