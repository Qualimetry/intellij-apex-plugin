public class SingleEntrySingleton {
    private static SingleEntrySingleton instance;

    public static SingleEntrySingleton getInstance() {
        if (instance == null) {
            instance = new SingleEntrySingleton();
        }
        return instance;
    }

    public void doEverything(Account a) {
        a.Name = a.Name.trim();
        a.Description = 'Processed';
        a.Industry = 'Technology';
        a.Rating = 'Hot';
        update a;
    }
}
