public class Ctr {
    public void run(List<Contact> contacts) {
        List<Messaging.SingleEmailMessage> msgs = new List<Messaging.SingleEmailMessage>();
        for (Contact c : contacts) {
            msgs.add(build(c));
        }
        Messaging.sendEmail(msgs);
    }
    Messaging.SingleEmailMessage build(Contact c) { return null; }
}
