public class SafeExceptionHandler {
    public String getAccountName(Id accountId) {
        try {
            Account a = [SELECT Name FROM Account WHERE Id = :accountId];
            return a.Name;
        } catch (QueryException e) {
            return 'Unknown';
        }
    }

    public void processRecord(SObject record) {
        String val = (String) record.get('Name');
        if (val != null) {
            System.debug(val.toUpperCase());
        }
    }

    public Decimal calculateTotal(List<Order> orders) {
        Decimal total = 0;
        for (Order o : orders) {
            if (o.TotalAmount != null) {
                total += o.TotalAmount;
            }
        }
        return total;
    }

    public void safeOperation() {
        try {
            doWork();
        } catch (DmlException e) {
            System.debug('DML error: ' + e.getMessage());
        } catch (Exception e) {
            System.debug('General error: ' + e.getMessage());
        }
    }

    private void doWork() {
        insert new Account(Name = 'Test');
    }
}
