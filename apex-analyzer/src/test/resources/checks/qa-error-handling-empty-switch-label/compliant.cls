public class CompliantSwitchHandler {
    public void handleStatus(Integer code) {
        switch (code) {
            case 1:
                handleNew();
                break;
            case 2:
                handleActive();
                break;
            case 3:
                handleClosed();
                break;
        }
    }

    void handleNew() { }
    void handleActive() { }
    void handleClosed() { }
}
