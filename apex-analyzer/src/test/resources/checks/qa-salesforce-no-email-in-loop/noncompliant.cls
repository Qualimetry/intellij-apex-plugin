public class NotificationService {
    private String defaultSubject = 'Alert';

    public void notifyContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            List<String> toAddresses = new List<String>{c.Email};
            String body = 'Dear ' + c.FirstName;
            sendAlert(toAddresses, body);
            Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{});
        }
    }

    private void sendAlert(List<String> addrs, String body) { }
}
