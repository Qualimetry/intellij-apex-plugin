public final class CurrencyConverter {
    protected Decimal exchangeRate;
    protected String baseCurrency;

    public Decimal convert(Decimal amount) {
        return amount * exchangeRate;
    }

    public String getCurrency() {
        return baseCurrency;
    }
}
