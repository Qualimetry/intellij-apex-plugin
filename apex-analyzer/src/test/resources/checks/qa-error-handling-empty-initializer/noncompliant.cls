public class EmptyInitializerExample {
    {}
    private String name;
    private Integer count;

    public void run() {
        doWork();
    }

    public class InnerService {
    {}
    }

    public void process(List<Account> accounts) {
        for (Account acc : accounts) {
            acc.Name = 'Processed';
        }
        update accounts;
    }

    private void doWork() { }
}
