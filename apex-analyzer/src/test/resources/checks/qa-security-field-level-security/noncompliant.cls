public class NoFLS {
    public void readSalary(Account a) {
        Decimal sal = a.Salary__c;
        System.debug('Salary: ' + sal);
    }

    public void updateRating(Account a, String rating) {
        a.Rating__c = rating;
        update a;
    }

    public List<Account> getAccounts() {
        return [SELECT Id, SSN__c, Salary__c FROM Account LIMIT 50];
    }
}
