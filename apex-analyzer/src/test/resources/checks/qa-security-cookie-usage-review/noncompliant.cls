public class UsesCookie {
    public void storePreference(String name, String value) {
        Cookie c = new Cookie(name, value, null, 3600, true);
        ApexPages.currentPage().setCookies(new List<Cookie>{ c });
    }

    public String readPreference(String name) {
        Cookie c = ApexPages.currentPage().getCookies().get(name);
        return c != null ? c.getValue() : null;
    }
}
