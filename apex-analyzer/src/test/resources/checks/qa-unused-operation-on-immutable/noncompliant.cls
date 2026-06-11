public class ImmutableBug {
    public void processName(String name) {
        name.trim();
        name.toLowerCase();
        name.replace('old', 'new');
    }

    public void formatDate(Date d) {
        String formatted = String.valueOf(d);
        formatted.toUpperCase();
    }
}
