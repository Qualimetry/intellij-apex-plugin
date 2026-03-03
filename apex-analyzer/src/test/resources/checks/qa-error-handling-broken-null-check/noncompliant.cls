public class BrokenNullCheck {
    public void run(Object x, List<String> items) {
        if (x.getName() != null && x != null) {
            System.debug(x);
        }
        if (x != null || x.size() > 0) {
            System.debug(items);
        }
    }

    public void assignMethodResult(Contact c) {
        if (c = getContact()) {
            c.Email = 'test@example.com';
        }
    }

    public void assignVariable(String s) {
        if (s = getValue()) {
            System.debug(s);
        }
    }

    Contact getContact() { return null; }
    String getValue() { return null; }
}
