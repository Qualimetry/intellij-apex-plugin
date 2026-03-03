public class TypeValidator {

    public Boolean isAccount(SObject record) {
        return record != null && record instanceof Account;
    }

    public Boolean isContact(Object obj) {
        if (obj != null && obj instanceof Contact) {
            return true;
        }
        return false;
    }

    public void processRecord(SObject record) {
        if (record != null && record instanceof Lead) {
            Lead ld = (Lead) record;
            ld.Status = 'Working';
        }
    }
}
