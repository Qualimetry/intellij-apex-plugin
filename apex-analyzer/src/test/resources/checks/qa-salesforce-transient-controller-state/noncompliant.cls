public class MyController {
    public List<Account> accounts;
    public Decimal total;
    public Map<Id, String> nameMap;
    public String selectedFilter;

    public MyController() {
        accounts = [SELECT Id, Name FROM Account LIMIT 100];
        total = 0;
        for (Account a : accounts) {
            total += 1;
        }
        nameMap = new Map<Id, String>();
    }
}
