public class NonCompliant {
    public String normalize(String s) {
        return s.toLowerCase().toUpperCase();
    }

    public String process(String input) {
        String clean = input.trim();
        return clean.toUpperCase().toLowerCase();
    }

    public void logValue(String val) {
        System.debug(val.toLowerCase());
    }

    public String format(String text) {
        String result = text.toUpperCase().toLowerCase().trim();
        return result;
    }
}
