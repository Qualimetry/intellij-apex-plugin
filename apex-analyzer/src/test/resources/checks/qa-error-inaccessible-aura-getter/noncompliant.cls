public class ContactController {
    private String contactName;

    @AuraEnabled
    private String name { get; set; }

    @AuraEnabled
    static List<Contact> getContacts() {
        return [SELECT Id, Name FROM Contact LIMIT 10];
    }

    public String getContactName() {
        return contactName;
    }
}
