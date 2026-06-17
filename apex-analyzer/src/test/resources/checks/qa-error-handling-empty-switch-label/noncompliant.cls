public class NonCompliantSwitchHandler {
    public void handleCode(Integer code) {
        switch (code) {
            case 1:
            case 2:
                doFirst();
                break;
            case 3:
            case 4:
                doSecond();
                break;
        }
    }

    void doFirst() { }
    void doSecond() { }
}
