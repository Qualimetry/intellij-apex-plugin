public class WithFLS {
    public void readSalary(Account a) {
        if (Schema.sObjectType.Account.fields.Salary__c.isAccessible()) {
            Decimal sal = a.Salary__c;
        }
    }

    public void updateRating(Account a, String rating) {
        if (Schema.sObjectType.Account.fields.Rating__c.isUpdateable()) {
            a.Rating__c = rating;
            update a;
        }
    }

    public List<Account> getAccounts() {
        if (Schema.sObjectType.Account.fields.SSN__c.isAccessible()) {
            return [SELECT Id, SSN__c FROM Account LIMIT 50];
        }
        return new List<Account>();
    }
}
