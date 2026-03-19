public class ImmutableFixed {
    public String processName(String name) {
        name = name.trim();
        name = name.toLowerCase();
        name = name.replace('old', 'new');
        return name;
    }

    public String formatDate(Date d) {
        String formatted = String.valueOf(d);
        return formatted.toUpperCase();
    }
}
