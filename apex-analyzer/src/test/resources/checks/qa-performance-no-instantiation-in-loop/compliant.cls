public class NoNewInLoop {
    public List<String> getNames(List<Account> accounts) {
        List<String> results = new List<String>();
        for (Account a : accounts) {
            results.add(a.Name);
        }
        return results;
    }

    public void logContacts(List<Contact> contacts) {
        for (Contact c : contacts) {
            System.debug(c.Name);
        }
    }
}
