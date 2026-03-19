public class SoqlInGetter {
    public List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account LIMIT 10];
    }

    public Integer getContactCount() {
        return [SELECT COUNT() FROM Contact];
    }

    public List<Opportunity> getOpps() {
        return [SELECT Id, Amount FROM Opportunity WHERE IsClosed = false LIMIT 5];
    }
}
