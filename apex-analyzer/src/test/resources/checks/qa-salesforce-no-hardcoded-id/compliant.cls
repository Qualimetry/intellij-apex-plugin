public class RecordService {
    private Id profileId;

    public RecordService() {
        this.profileId = AppConfig__c.getInstance().ProfileId__c;
    }

    public List<User> getUsers() {
        return [SELECT Id, Name FROM User WHERE ProfileId = :profileId];
    }

    public Id getProfileId() {
        return profileId;
    }
}
