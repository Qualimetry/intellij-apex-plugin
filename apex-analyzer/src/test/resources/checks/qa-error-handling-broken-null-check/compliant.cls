public class CompliantNullCheck {
    public void run(Object x) {
        if (x != null && x.getName() != null) {
            System.debug(x);
        }
        if (x == null || x.getName() == null) {
            return;
        }
    }
}
