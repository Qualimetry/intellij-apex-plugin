public class ContactControllerCompliant {
    private String contactName;

    @AuraEnabled
    public String name { get; set; }

    @AuraEnabled
    public static List<Contact> getContacts() {
        return [SELECT Id, Name FROM Contact LIMIT 10];
    }

    public String getContactName() {
        return contactName;
    }
}
