public class TypeChecker {
    public Account toAccount(Object obj) {
        if (obj instanceof Account) {
            return (Account) obj;
        }
        return null;
    }

    public Contact toContact(Object record) {
        if (record instanceof Contact) {
            Contact c = (Contact) record;
            return c;
        }
        return null;
    }

    public void processItems(List<Object> items) {
        for (Object item : items) {
            if (item instanceof Opportunity) {
                Opportunity opp = (Opportunity) item;
                System.debug(opp.Name);
            }
        }
    }
}
