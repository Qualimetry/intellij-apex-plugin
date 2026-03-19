public virtual class BaseService {
    private String config;

    public BaseService() {
        doLoadConfig();
        doInit();
    }

    private void doLoadConfig() {
        config = 'default';
    }

    private void doInit() { }

    public virtual void loadConfig() {
        config = 'default';
    }
}
