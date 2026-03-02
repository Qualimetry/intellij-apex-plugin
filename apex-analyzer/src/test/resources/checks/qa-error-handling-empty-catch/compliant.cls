public class Ctr {
    public void run() {
        try {
            doWork();
        } catch (QueryException e) {
            throw new MyException(e);
        }
    }
    void doWork() { }
}
