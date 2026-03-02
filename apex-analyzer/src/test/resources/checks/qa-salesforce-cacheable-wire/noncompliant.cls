public with sharing class ContactController {
    @AuraEnabled
    public static List<Contact> getContacts() {
        return [SELECT Id, Name, Email FROM Contact LIMIT 50];
    }

    @AuraEnabled
    public static Contact getContactById(Id contactId) {
        return [SELECT Id, Name, Email, Phone FROM Contact WHERE Id = :contactId];
    }

    public String getGreeting() {
        return 'Hello';
    }
}
