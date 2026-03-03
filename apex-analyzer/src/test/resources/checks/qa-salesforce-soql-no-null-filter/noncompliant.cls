public class SoqlNull {
    public List<Account> getWithoutIndustry() {
        return [SELECT Id FROM Account WHERE Industry = null];
    }

    public List<Contact> getOrphaned() {
        return [SELECT Id FROM Contact WHERE AccountId = null];
    }

    public List<Opportunity> getNoAmount() {
        return [SELECT Id FROM Opportunity WHERE Amount = null];
    }
}
