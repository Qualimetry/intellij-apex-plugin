public class OrderProcessor {
    public void processOrders(List<Order> orders) {
        for (Order o : orders) {
            if (o.Status == 'Draft') {
                o.Status = 'Pending';
            }
            o.Priority__c = 'Normal';
            update o;
        }
    }

    public Account findActive(List<Account> accounts) {
        Account result = null;
        for (Account a : accounts) {
            if (a.IsActive__c) {
                result = a;
            }
        }
        return result;
    }

    public Integer countMatches(List<Contact> contacts) {
        Integer count = 0;
        while (contacts.size() > 0) {
            Contact c = contacts.remove(0);
            if (c.Email != null) {
                count++;
            }
        }
        return count;
    }
}
