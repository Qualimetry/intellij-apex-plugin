public class NonCompliant {
    private static final String PREFIX = 'NS_';

    public static String buildKey(String name) {
        return PREFIX + name;
    }

    public static void logActivity(String message) {
        System.debug(LoggingLevel.INFO, message);
    }

    public static Integer countRecords(List<SObject> records) {
        return records.size();
    }
}
