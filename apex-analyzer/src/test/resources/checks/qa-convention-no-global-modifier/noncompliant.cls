global class MyService {
    global static void doWork() {
        System.debug('working');
    }

    public void process() {
        doWork();
    }

    public String getName() {
        return 'MyService';
    }
}
