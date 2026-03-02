public class EventHandler {

    private Boolean isActive;
    private Integer eventCount;

    public EventHandler(Boolean active) {
        this.isActive = active;
        this.eventCount = 0;
    }

    public void handleEvent(Boolean flag) {
        if (flag) { }
    }

    public Integer getEventCount() {
        return eventCount;
    }
}
