public abstract class EventHandler {
    public virtual void onBefore() { }
    public virtual void onAfter() { }

    public void dispatch(String eventType) {
        onBefore();
        processEvent(eventType);
        onAfter();
    }

    private void processEvent(String eventType) {
        System.debug(eventType);
    }
}
