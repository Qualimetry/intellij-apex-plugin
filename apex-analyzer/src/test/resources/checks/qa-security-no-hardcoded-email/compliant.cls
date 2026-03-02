public class ConfigurableEmail {
    public String getAdminEmail() {
        return OrgSettings__c.getInstance().Admin_Email__c;
    }

    public void sendAlert(String body) {
        Messaging.SingleEmailMessage msg = new Messaging.SingleEmailMessage();
        String recipient = OrgSettings__c.getInstance().Ops_Email__c;
        msg.setToAddresses(new List<String>{ recipient });
        msg.setSubject('Alert');
        msg.setPlainTextBody(body);
        Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ msg });
    }
}
