public class AccountService {
    public String getAccountName(Account acct) {
        if (acct == null) {
            return 'Unknown';
        }
        String name = acct.Name;
        return name;
    }

    public void processContact(Contact c) {
        if (c != null) {
            String email = c.Email;
            System.debug(email);
        }
    }

    public Boolean hasName(Account a) {
        if (a == null) {
            return false;
        }
        return a.Name != null;
    }
}
