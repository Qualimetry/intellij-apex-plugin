public abstract class EventHandler {
    public abstract void onBefore();
    public abstract void onAfter();

    public void dispatch(String eventType) {
        onBefore();
        processEvent(eventType);
        onAfter();
    }

    private void processEvent(String eventType) {
        System.debug(eventType);
    }
}
