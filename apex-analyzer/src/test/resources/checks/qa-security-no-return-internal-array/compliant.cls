public class SafeReturn {
    private List<Id> recordIds = new List<Id>();
    private List<String> tags = new List<String>();

    public List<Id> getRecordIds() {
        return recordIds.clone();
    }

    public List<String> getTags() {
        return new List<String>(tags);
    }

    public void addRecord(Id recId) {
        recordIds.add(recId);
    }
}
