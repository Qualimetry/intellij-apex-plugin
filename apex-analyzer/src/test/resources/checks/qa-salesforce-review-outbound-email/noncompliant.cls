public class SendEmail {
    public void notifyAdmin(String body) {
        Messaging.SingleEmailMessage msg = new Messaging.SingleEmailMessage();
        msg.setToAddresses(new List<String>{ 'admin@company.com' });
        msg.setSubject('Notification');
        msg.setPlainTextBody(body);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ msg });
    }

    public void sendWelcome(Contact c) {
        Messaging.SingleEmailMessage msg = new Messaging.SingleEmailMessage();
        msg.setTargetObjectId(c.Id);
        msg.setTemplateId('00X000000000001');
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ msg });
    }
}
