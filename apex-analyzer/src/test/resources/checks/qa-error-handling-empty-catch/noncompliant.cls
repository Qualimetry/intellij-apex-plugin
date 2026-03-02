public class Ctr {
    public void run() {
        try {
            doWork();
        } catch (Exception e) { }
        try {
            doMore();
        } catch (QueryException ex) {
        }
    }
    void doWork() { }
    void doMore() { }
}
