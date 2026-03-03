public class UnsafeService {
    private List<Id> accountIds;
    private List<String> names;

    public void setAccountIds(List<Id> ids) {
        this.accountIds = ids;
    }

    public void setNames(List<String> nameList) {
        this.names = nameList;
    }

    public void process() {
        for (Id aid : accountIds) {
            System.debug(aid);
        }
    }
}
