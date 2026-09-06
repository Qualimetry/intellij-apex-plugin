public class ContactMerger {
    public List<Contact> mergeContacts(List<Contact> primary, List<Contact> secondary) {
        List<Contact> merged = new List<Contact>();
        for (Contact c : primary) {
            merged.add(c);
        }
        for (Contact c : secondary) {
            merged.add(c);
        }
        return merged;
    }
}
