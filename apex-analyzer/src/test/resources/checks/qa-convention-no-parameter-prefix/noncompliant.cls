public class AccountService {
    public void createAccount(String p_name, Integer p_count) {
        doWork(p_name);
    }

    public void updateStatus(String a_name, Boolean active) {
        save(a_name);
    }

    public void validate(String label) {
        check(label);
    }
}
