public class AccountValidator {
    public Boolean validate(Account acc) {
        return acc.Name != null;
    }

    public void process() {
        Account a = new Account(Name = 'Test');
    }

    public String validateName(String name) {
        return name;
    }
}
