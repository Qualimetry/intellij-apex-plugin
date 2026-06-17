public class UnsafeQuery {
    public List<Account> findByName(String name) {
        String q = 'SELECT Id, Name, Phone FROM Account ' +
            'WHERE Name LIKE \'%' + name + '%\'';
        return Database.query(q);
    }

    public List<Contact> searchContacts(String email) {
        String query = 'SELECT Id FROM Contact WHERE Email = \'' + email + '\'';
        return Database.query(query);
    }
}
