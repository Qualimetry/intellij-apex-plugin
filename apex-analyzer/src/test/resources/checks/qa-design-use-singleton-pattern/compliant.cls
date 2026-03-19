public class Compliant {
    private static Compliant instance;

    private Compliant() { }

    public static Compliant getInstance() {
        if (instance == null) {
            instance = new Compliant();
        }
        return instance;
    }

    public static String buildKey(String name) {
        return 'KEY_' + name;
    }

    public void processRecord(Account acc) {
        acc.Name = acc.Name.trim();
    }
}
