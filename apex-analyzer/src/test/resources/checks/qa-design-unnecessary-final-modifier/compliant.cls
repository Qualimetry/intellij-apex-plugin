public class ContactService {

    private Boolean active;

    public ContactService() {
        this.active = true;
    }

    public final String getName() {
        return 'ContactService';
    }

    public final void initialize() {
        this.active = true;
        System.debug('Initialized');
    }

    public void processContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            c.Description = 'Processed';
        }
    }
}
