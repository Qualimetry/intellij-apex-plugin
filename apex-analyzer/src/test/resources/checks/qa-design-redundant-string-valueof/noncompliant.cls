public class NonCompliant {
    public String convertName(String aString) {
        return String.valueOf(aString);
    }

    public String formatLabel() {
        Integer count = 5;
        return String.valueOf(count);
    }

    public String process(String myString) {
        String result = String.valueOf(myString);
        return result;
    }

    public String buildKey(Account acc) {
        String keyString = acc.Name;
        return String.valueOf(keyString);
    }
}
