public class AccountService {
    private String orgId;
    private Boolean isActive;

    public AccountService() {
        this.orgId = UserInfo.getOrganizationId();
        this.isActive = false;
    }

    public void activate() {
        isActive = true;
    }
}
