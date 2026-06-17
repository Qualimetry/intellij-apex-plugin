public abstract class AccountHelper {
    private String region;

    public AccountHelper(String region) {
        this.region = region;
    }

    public abstract void process();
    public abstract List<Account> query();
}

public abstract class OpportunityUtils {
    public abstract void validate();
}
