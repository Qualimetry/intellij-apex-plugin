public class RecordService {
    private Id profileId;

    public RecordService() {
        this.profileId = '00e000000000001AAA';
    }

    public List<User> getUsers() {
        return [SELECT Id, Name FROM User WHERE ProfileId = :profileId];
    }

    public Id getProfileId() {
        return profileId;
    }
}
