public class AccountProcessor {

    private String name;
    private Integer count;

    public void processAccount(Account acc) {
        String accountName = acc.Name;
        Integer a = 1;
        Integer b = a;
        this.name = acc.Name;
    }

    public void reset() {
        this.count = 0;
    }
}
