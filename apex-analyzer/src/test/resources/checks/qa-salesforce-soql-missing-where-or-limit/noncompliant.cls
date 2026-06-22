public class SoqlNoWhere {
    public List<Account> loadAll() {
        return [SELECT Id, Name, Industry FROM Account];
    }

    public List<Contact> getContacts() {
        return [SELECT Id, FirstName, LastName
                FROM Contact
                ORDER BY LastName];
    }

    public void processOpportunities() {
        List<Opportunity> opps = [SELECT Id, Amount, StageName FROM Opportunity];
        for (Opportunity opp : opps) {
            opp.Description = 'Reviewed';
        }
        update opps;
    }
}
