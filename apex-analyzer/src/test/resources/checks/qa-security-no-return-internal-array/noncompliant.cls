public class UnsafeReturn {
    private List<Id> recordIds = new List<Id>();

    public List<Id> getRecordIds() {
        return this.recordIds;
    }

    public void addRecord(Id recId) {
        this.recordIds.add(recId);
    }

    public Integer getCount() {
        Integer count = this.recordIds.size();
        return count;
    }
}
