public abstract class AbstractAccountHelper {
    private String region;

    public AbstractAccountHelper(String region) {
        this.region = region;
    }

    public abstract void process();
    public abstract List<Account> query();
}

public abstract class BaseOpportunityUtils {
    public abstract void validate();
}
