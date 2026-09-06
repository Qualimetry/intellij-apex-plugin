public class NpeHandlerClass {
    public String getAccountName(Id accountId) {
        try {
            Account a = [SELECT Name FROM Account WHERE Id = :accountId];
            return a.Name;
        } catch (NullPointerException e) {
            return 'Unknown';
        }
    }

    public void processRecord(SObject record) {
        try {
            String val = (String) record.get('Name');
            System.debug(val.toUpperCase());
        } catch (NullPointerException npe) {
            System.debug('Null value encountered');
        }
    }

    public Decimal calculateTotal(List<Order> orders) {
        Decimal total = 0;
        for (Order o : orders) {
            try {
                total += o.TotalAmount;
            } catch (NullPointerException ex) {
                total += 0;
            }
        }
        return total;
    }
}
