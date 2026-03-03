public class TypeChecker {

    public Boolean isAccount(SObject record) {
        return record instanceof Account;
    }

    public String getTypeName(Object obj) {
        if (obj instanceof Contact) {
            return 'Contact';
        } else if (obj instanceof Lead) {
            return 'Lead';
        }
        return 'Unknown';
    }

    public Boolean isValid(SObject record) {
        if (record == null) {
            return false;
        }
        return record instanceof Account;
    }
}
