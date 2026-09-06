public abstract class BaseHelper {

    private String helperName;
    private Integer processCount;

    public BaseHelper(String name) {
        this.helperName = name;
        this.processCount = 0;
    }

    public void doWork() {
        processCount++;
        System.debug('Processing: ' + helperName);
    }

    public String getName() {
        return helperName;
    }
}
