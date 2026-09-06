public class NotificationService {

    private String channel;

    public NotificationService() {
        this.channel = 'default';
    }

    public void sendNotifications() {
        String body1 = '{"value":"hello"}';
        String body2 = '{"count":"hello"}';
    }

    private void log(String message) {
        System.debug(channel + ': ' + message);
    }
}
