public class Compliant {
    public void checkValues(String name, Integer count, Boolean active) {
        if (null == name) {
            return;
        }
        if ('Admin' == name) {
            System.debug('Found admin');
        }
        Boolean isZero = (0 == count);
        Boolean isActive = (true == active);
        // x == 'hello' is just a comment, not real code
        String result = name + ' processed';
        Integer total = count + 10;
    }
}
