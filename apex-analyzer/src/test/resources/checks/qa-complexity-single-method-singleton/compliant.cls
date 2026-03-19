public class AccountService {
    private final String tenantId;

    public AccountService(String tenantId) {
        this.tenantId = tenantId;
    }

    public void processAccount(Account a) {
        a.Name = a.Name.trim();
        a.Description = 'Processed by ' + tenantId;
    }

    public void setDefaults(Account a) {
        a.Industry = 'Technology';
        a.Rating = 'Hot';
    }
}
