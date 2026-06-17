public class ObjectParams {
    public void process(Object data) {
        String s = (String) data;
        doWork(s);
    }

    public Decimal calculate(Object amount, Object rate) {
        Decimal a = (Decimal) amount;
        Decimal r = (Decimal) rate;
        return a * r;
    }
}
