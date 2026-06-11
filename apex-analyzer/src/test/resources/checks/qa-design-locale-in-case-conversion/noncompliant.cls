public class NonCompliant {
    public String convertLower(String s) {
        return s.toLowerCase();
    }

    public String convertUpper(String s) {
        return s.toUpperCase();
    }

    public void processRecords(List<Account> accounts) {
        for (Account acc : accounts) {
            acc.Name = acc.Name.toLowerCase(Locale.ENGLISH);
        }
    }

    public String normalize(String input) {
        String trimmed = input.trim();
        return trimmed.toLowerCase();
    }
}
