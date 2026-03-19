public class CompliantNullHandler {
    public Boolean isValid(String value) {
        if (value == null) {
            return false;
        }
        return value.length() > 0;
    }

    public String getDefault(String input, String fallback) {
        if (input != null && input.length() > 0) {
            return input;
        }
        return fallback;
    }

    public Boolean areEqual(String a, String b) {
        if (a == null || b == null) {
            return a == b;
        }
        return a.equals(b);
    }
}
