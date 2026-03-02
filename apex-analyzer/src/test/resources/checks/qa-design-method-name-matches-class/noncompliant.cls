public class AccountValidator {
    public Boolean AccountValidator(Account acc) {
        return acc.Name != null;
    }

    public void process() {
        Account a = new Account(Name = 'Test');
    }

    public String AccountValidator(String name) {
        return name;
    }
}
