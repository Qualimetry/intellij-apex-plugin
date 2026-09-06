public class Calculator {
    public Integer a() {
        return 1;
    }

    public void b() {
        System.debug('done');
    }

    public void process() {
        Integer result = a();
        b();
    }
}
