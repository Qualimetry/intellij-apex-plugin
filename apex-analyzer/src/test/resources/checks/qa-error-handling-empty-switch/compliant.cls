public class StatusHandler {
    public void processStatus(Integer code) {
        switch (code) {
            case 1:
                handleActive();
                break;
            case 2:
                handleInactive();
                break;
            default:
                handleDefault();
                break;
        }
    }

    public void processType(String type) {
        switch (type) {
            case 'A':
                doTypeA();
                break;
        }
    }

    void handleActive() { }
    void handleInactive() { }
    void handleDefault() { }
    void doTypeA() { }
}
