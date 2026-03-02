public class Outer {
    public static void outerMethod() {
        System.debug('outer');
    }

    public class Inner {
        static Integer count = 0;
        static final String LABEL = 'inner';

        public static void innerMethod() {
            System.debug(count);
        }
    }
}
