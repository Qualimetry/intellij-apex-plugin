public class Outer {
    static Integer outerCount = 0;
    static final String CONST = 'value';

    public static void outerMethod() {
        System.debug('outer');
    }

    public class Inner {
        private Integer value;

        public void doWork() {
            System.debug(value);
        }
    }
}
