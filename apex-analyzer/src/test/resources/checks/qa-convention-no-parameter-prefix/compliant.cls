public class AccountService {
    public void createAccount(String name, String industry) {
        Account acc = new Account(Name = name, Industry = industry);
        insert acc;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public void validate(String label) {
        check(label);
    }
}
