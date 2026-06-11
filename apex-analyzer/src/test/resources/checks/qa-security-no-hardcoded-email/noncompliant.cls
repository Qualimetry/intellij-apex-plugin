public class HardcodedEmail {
    public String getAdminEmail() {
        return 'admin@company.com';
    }

    public void sendAlert(String body) {
        Messaging.SingleEmailMessage msg = new Messaging.SingleEmailMessage();
        msg.setToAddresses(new List<String>{ 'ops-team@company.com' });
        msg.setSubject('Alert');
        msg.setPlainTextBody(body);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ msg });
    }
}
