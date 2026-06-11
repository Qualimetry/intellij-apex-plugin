public class TypedParams {
    public void process(String data) {
        doWork(data);
    }

    public Decimal calculate(Decimal amount, Decimal rate) {
        return amount * rate;
    }
}
