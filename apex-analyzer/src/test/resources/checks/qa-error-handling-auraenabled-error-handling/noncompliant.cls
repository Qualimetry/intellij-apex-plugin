public class BadLightningController {
    @AuraEnabled
    public static List<Account> getAccounts() {
        return [SELECT Id, Name FROM Account LIMIT 100];
    }

    @AuraEnabled
    public static Contact getContact(Id contactId) {
        return [SELECT Id FROM Contact WHERE Id = :contactId];
    }

    @AuraEnabled(cacheable=true)
    public static String getName() {
        return 'Hello';
    }

    public static void helperMethod() {
        System.debug('Not aura-enabled, no issue');
    }
}
