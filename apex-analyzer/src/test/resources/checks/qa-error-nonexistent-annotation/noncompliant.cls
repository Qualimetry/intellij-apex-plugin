public class OrderService {
    private String status;

    public OrderService(String status) {
        this.status = status;
    }

    @Override
    public void processOrder(Order o) {
        o.Status = 'Shipped';
        update o;
    }

    @Nullable
    public Account findAccount(String name) {
        List<Account> accts = [SELECT Id FROM Account WHERE Name = :name LIMIT 1];
        return accts.isEmpty() ? null : accts[0];
    }
}
