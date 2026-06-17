public class SoqlNegative {
    public List<Account> getNonNull() {
        return [SELECT Id FROM Account WHERE Name != null];
    }

    public List<Contact> getFiltered(Set<Id> excludeIds) {
        return [SELECT Id FROM Contact WHERE Id NOT IN :excludeIds];
    }

    public List<Opportunity> getOpen() {
        return [SELECT Id FROM Opportunity WHERE StageName != 'Closed Won'];
    }
}
