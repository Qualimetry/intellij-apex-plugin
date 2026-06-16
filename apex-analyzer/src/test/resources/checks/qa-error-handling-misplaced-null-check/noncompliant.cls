public class AccountProcessor {
    public String getName(Account acct) {
        if (acct.Name == null) {
            return 'None';
        }
        return acct.Name;
    }

    public void validate(Contact c) {
        String email = c.Email;
        if (c == null) {
            return;
        }
        System.debug(email);
    }

    public Boolean checkRecord(SObject record) {
        Boolean valid = record.Id != null;
        Boolean isNull = null == record.Name;
        return valid && !isNull;
    }
}
