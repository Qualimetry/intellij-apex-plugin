public class TransientController {
    public transient List<Account> accounts;
    public transient Decimal total;
    public transient Map<Id, String> nameMap;
    public transient String selectedFilter;

    public TransientController() {
        loadData();
    }

    private void loadData() {
        accounts = [SELECT Id, Name FROM Account LIMIT 100];
        total = accounts.size();
        nameMap = new Map<Id, String>();
    }
}
