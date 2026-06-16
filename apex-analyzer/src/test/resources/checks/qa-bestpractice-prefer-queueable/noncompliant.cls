public class AsyncService {
    private String accountName;

    @Future
    public static void processOrderAsync(Set<Id> orderIds) {
        List<Order> orders = [SELECT Id, Status FROM Order WHERE Id IN :orderIds];
        for (Order o : orders) {
            o.Status = 'Processed';
        }
        update orders;
    }

    @future(callout=true)
    public static void sendConfirmation(String endpoint) {
        Http h = new Http();
        HttpRequest req = new HttpRequest();
        req.setEndpoint(endpoint);
        req.setMethod('POST');
        h.send(req);
    }
}
