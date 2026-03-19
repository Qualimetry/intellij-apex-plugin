public class LightningController {
    @AuraEnabled
    public static List<Account> getAccounts() {
        try {
            return [SELECT Id, Name FROM Account LIMIT 100];
        } catch (Exception e) {
            throw new AuraHandledException(e.getMessage());
        }
    }

    @AuraEnabled(cacheable=true)
    public static Contact getContact(Id contactId) {
        try {
            return [SELECT Id, FirstName, LastName FROM Contact WHERE Id = :contactId];
        } catch (QueryException e) {
            throw new AuraHandledException('Contact not found: ' + e.getMessage());
        }
    }

    @AuraEnabled
    public static void saveRecord(Account acc) {
        try {
            upsert acc;
        } catch (DmlException e) {
            throw new AuraHandledException('Save failed: ' + e.getMessage());
        }
    }
}
