public class ContactMerger {
    public List<Contact> mergeContacts(List<Contact> primary, List<Contact> secondary) {
        List<Contact> merged = new List<Contact>();
        merged.addAll(primary);
        merged.addAll(secondary);
        return merged;
    }

    public void addSingle(List<Contact> target, Contact c) {
        target.add(c);
    }
}
