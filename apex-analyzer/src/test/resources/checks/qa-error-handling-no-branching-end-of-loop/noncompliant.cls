public class BadLoopController {
    public void processRecords(List<Account> accounts) {
        for (Account a : accounts) {
            if (a.Name == null) return;
        }
    }

    public void filterContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            if (c.Email == null) continue;
        }
    }

    public void findFirst(List<Opportunity> opps) {
        for (Opportunity o : opps) {
            processOpp(o);
            break;
        }
        return;
    }

    private void processOpp(Opportunity o) {
        o.StageName = 'Closed';
    }
}
