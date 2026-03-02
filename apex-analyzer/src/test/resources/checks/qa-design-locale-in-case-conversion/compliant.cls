public class Compliant {
    public String convertLower(String s) {
        return s.toLowerCase(Locale.ENGLISH);
    }

    public String convertUpper(String s) {
        return s.toUpperCase(Locale.US);
    }

    // toLowerCase() mentioned in a comment is fine
    public void processName(String name) {
        String normalized = name.trim().toLowerCase(Locale.ROOT);
        System.debug(normalized);
    }

    public String formatLabel(String label) {
        return label.toUpperCase(Locale.ENGLISH) + '_SUFFIX';
    }
}
