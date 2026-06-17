public class CleanConstructor {
    private String name;
    private AccountService service;

    public CleanConstructor(String name) {
        this.name = name;
        this.service = AccountServiceFactory.create();
    }

    private void initDefaults() {
        if (String.isBlank(name)) {
            name = 'Default';
        }
    }
}
