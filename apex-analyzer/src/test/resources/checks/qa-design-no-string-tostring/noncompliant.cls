public class NonCompliant {
    public String getDefault() {
        return 'default'.toString();
    }

    public void logMessage(String msg) {
        System.debug('Message: ' + msg.toString());
    }

    public String buildKey(String part) {
        String key = part + '_'.toString();
        return key;
    }

    public Boolean hasValue(String val) {
        return val != null && val.length() > 0;
    }
}
