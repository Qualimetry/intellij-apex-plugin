public class SoqlWithWhere {
    public List<Account> loadActive() {
        return [SELECT Id, Name, Industry FROM Account
                WHERE IsActive__c = true LIMIT 200];
    }

    public List<Contact> getContacts(Id accountId) {
        return [SELECT Id, FirstName, LastName
                FROM Contact
                WHERE AccountId = :accountId
                ORDER BY LastName];
    }

    public void processOpportunities(Set<Id> oppIds) {
        List<Opportunity> opps = [SELECT Id, Amount, StageName
                                  FROM Opportunity
                                  WHERE Id IN :oppIds];
        for (Opportunity opp : opps) {
            opp.Description = 'Reviewed';
        }
        update opps;
    }
}
