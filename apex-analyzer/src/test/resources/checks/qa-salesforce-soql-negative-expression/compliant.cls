public class SoqlPositive {
    public List<Account> getRecentAccounts() {
        return [SELECT Id, Name FROM Account ORDER BY CreatedDate DESC LIMIT 10];
    }

    public List<Contact> getAllContacts() {
        return [SELECT Id, Email FROM Contact LIMIT 100];
    }

    public Integer countOpportunities() {
        return [SELECT COUNT() FROM Opportunity];
    }
}
