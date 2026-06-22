public class NotificationService {

    private static final String MSG = 'hello';
    private String channel;

    public NotificationService() {
        this.channel = 'default';
    }

    public void sendNotifications() {
        log(MSG);
    }

    private void log(String message) {
        System.debug(channel + ': ' + message);
    }
}
