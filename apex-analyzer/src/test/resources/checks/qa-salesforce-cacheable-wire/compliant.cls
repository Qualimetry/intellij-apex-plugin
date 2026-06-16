public with sharing class ContactControllerCompliant {
    @AuraEnabled(cacheable=true)
    public static List<Contact> getContacts() {
        return [SELECT Id, Name, Email FROM Contact LIMIT 50];
    }

    @AuraEnabled(cacheable=true)
    public static Contact getContactById(Id contactId) {
        return [SELECT Id, Name, Email, Phone FROM Contact WHERE Id = :contactId];
    }

    public String getGreeting() {
        return 'Hello';
    }
}
