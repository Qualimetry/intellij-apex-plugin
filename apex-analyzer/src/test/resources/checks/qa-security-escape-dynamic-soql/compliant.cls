public class SafeQuery {
    public List<Account> findByName(String name) {
        String q = 'SELECT Id, Name, Phone FROM Account WHERE Name LIKE \'%' + String.escapeSingleQuotes(name) + '%\'';
        return Database.query(q);
    }

    public List<Contact> searchContacts(String email) {
        String query = 'SELECT Id FROM Contact WHERE Email = \'' + String.escapeSingleQuotes(email) + '\'';
        return Database.query(query);
    }
}
