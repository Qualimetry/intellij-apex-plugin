public class NewInLoop {
    public void processRecords(List<Account> accounts) {
        for (Account a : accounts) {
            Wrapper w = new Wrapper();
            w.name = a.Name;
            w.industry = a.Industry;
            results.add(w);
        }
    }

    public void sendEmails(List<Contact> contacts) {
        for (Contact c : contacts) {
            Messaging.SingleEmailMessage msg = new Messaging.SingleEmailMessage();
            msg.setTargetObjectId(c.Id);
            Messaging.sendEmail(new List<Messaging.SingleEmailMessage>{ msg });
        }
    }
}
