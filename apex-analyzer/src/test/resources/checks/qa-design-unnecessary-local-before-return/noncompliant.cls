public class OpportunityService {

    public String getAccountName(Id accountId) {
        String name = [SELECT Name FROM Account WHERE Id = :accountId].Name;
        return name;
    }

    public Decimal getTotal(List<Opportunity> opps) {
        Decimal total = calculateSum(opps);
        return total;
    }

    public Boolean isActive(Account acc) {
        Boolean active = acc.Active__c == 'Yes';
        return active;
    }
}
