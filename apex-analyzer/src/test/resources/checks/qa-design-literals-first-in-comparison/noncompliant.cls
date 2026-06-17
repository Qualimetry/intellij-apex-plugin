public class NonCompliant {
    public void checkValues(String name, Integer count, Boolean active) {
        if (name == null) {
            return;
        }
        if (name == 'Admin') {
            System.debug('Found admin');
        }
        Boolean isSmall = count != 0;
        Boolean isActive = active == true;
        // status == 'Draft' is a comment, should not trigger
        String result = name + ' processed';
        Integer total = count + 10;
    }
}
