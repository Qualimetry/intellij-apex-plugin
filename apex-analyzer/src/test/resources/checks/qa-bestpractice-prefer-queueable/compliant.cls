public class AsyncCompliantService implements Queueable {
    private Set<Id> orderIds;

    public AsyncCompliantService(Set<Id> orderIds) {
        this.orderIds = orderIds;
    }

    public void execute(QueueableContext ctx) {
        List<Order> orders = [SELECT Id, Status FROM Order WHERE Id IN :orderIds];
        for (Order o : orders) {
            o.Status = 'Processed';
        }
        update orders;
    }
}
