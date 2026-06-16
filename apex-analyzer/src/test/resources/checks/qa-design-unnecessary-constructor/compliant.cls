public class AccountService {

    private String name;
    private Integer count;

    public AccountService() {
        this.name = 'Default';
        this.count = 0;
    }

    public AccountService(String name) {
        super();
        this.name = name;
    }

    public void process() {
        System.debug('Processing ' + name);
    }
}
