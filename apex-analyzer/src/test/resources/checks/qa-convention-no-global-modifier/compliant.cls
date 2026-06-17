public class MyService {
    public static void doWork() {
        System.debug('working');
    }

    public void process() {
        doWork();
    }

    public String getName() {
        return 'MyService';
    }
}
