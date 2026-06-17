public class FocusedService {
    private final AccountRepository repo;

    public FocusedService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account findById(Id accountId) {
        return repo.getById(accountId);
    }

    public void updateDescription(Account a, String desc) {
        a.Description = desc;
        repo.save(a);
    }
}
