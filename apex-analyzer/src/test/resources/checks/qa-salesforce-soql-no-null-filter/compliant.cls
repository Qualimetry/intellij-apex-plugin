public class SoqlNoNull {
    public List<Account> getByIndustry(String industry) {
        return [SELECT Id FROM Account WHERE Industry = :industry];
    }

    public List<Contact> getByAccount(Id accountId) {
        return [SELECT Id FROM Contact WHERE AccountId = :accountId];
    }

    public List<Opportunity> getWithAmount() {
        return [SELECT Id FROM Opportunity WHERE Amount > 0];
    }
}
