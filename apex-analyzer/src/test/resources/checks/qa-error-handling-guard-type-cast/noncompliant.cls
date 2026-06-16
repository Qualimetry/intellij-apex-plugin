public class TypeChecker {
    public Account toAccount(Object obj) {
        Account a = (Account) obj;
        return a;
    }

    public Contact toContact(Object record) {
        if (record != null) {
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
