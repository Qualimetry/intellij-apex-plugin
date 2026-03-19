public class SafeService {
    private List<Id> accountIds;
    private List<String> names;

    public void setAccountIds(List<Id> ids) {
        this.accountIds = ids != null ? new List<Id>(ids) : new List<Id>();
    }

    public void setNames(List<String> nameList) {
        this.names = nameList != null ? new List<String>(nameList) : new List<String>();
    }

    public void process() {
        for (Id aid : accountIds) {
            System.debug(aid);
        }
    }
}
