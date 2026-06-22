public with sharing class AccountData {
    @AuraEnabled
    public static List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account LIMIT 50];
    }

    @AuraEnabled
    public static Account getAccountById(Id accountId) {
        return [SELECT Id, Name FROM Account WHERE Id = :accountId];
    }
}
