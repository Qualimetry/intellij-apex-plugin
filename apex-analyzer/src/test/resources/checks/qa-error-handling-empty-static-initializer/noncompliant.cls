public class EmptyStaticInitExample {
    public static String name;
    static { }

    public static Integer count;

    public static void process() {
        System.debug(name);
    }

    public class InnerUtil {
        public static String label;
        static {}
    }

    public static void reset() {
        name = null;
        count = 0;
    }
}
