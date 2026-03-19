public class AccountService {
    private String orgId;
    private Boolean isActive;

    public void activate() {
        isActive = true;
    }

    public String getOrgId() {
        return orgId;
    }
}
