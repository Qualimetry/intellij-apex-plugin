public abstract class BaseProcessor {
    public String name;
    public Integer priority;

    public abstract void execute();
}

public abstract class EventHandler {
    protected Boolean isActive;

    public virtual void handle() {
        isActive = true;
    }
}
