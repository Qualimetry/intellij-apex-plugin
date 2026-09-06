public class StatusHandler {
    public void processStatus(Integer code) {
        switch (code) { }
    }

    public void processType(String type) {
        switch (type) {
            case 'A':
                doTypeA();
                break;
        }
    }

    public void handlePriority(Integer priority) {
        switch (priority) { }
    }

    void doTypeA() { }
}
