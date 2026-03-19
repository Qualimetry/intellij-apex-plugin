public virtual class BaseService {
    private String config;

    public BaseService() {
        loadConfig();
        init();
    }

    public virtual void loadConfig() {
        config = 'default';
    }

    virtual void init() { }
    private void doWork() { }
}
