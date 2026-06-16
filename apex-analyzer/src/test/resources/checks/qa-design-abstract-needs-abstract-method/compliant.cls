public abstract class BaseHelper {

    private String helperName;
    private Integer processCount;

    public BaseHelper(String name) {
        this.helperName = name;
        this.processCount = 0;
    }

    public abstract void doWork();

    public String getName() {
        return helperName;
    }
}
