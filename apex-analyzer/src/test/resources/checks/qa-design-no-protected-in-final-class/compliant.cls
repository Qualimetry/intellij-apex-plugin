public final class CurrencyConverter {
    private Decimal exchangeRate;
    private String baseCurrency;

    public CurrencyConverter(Decimal rate, String currency) {
        this.exchangeRate = rate;
        this.baseCurrency = currency;
    }

    public Decimal convert(Decimal amount) {
        return amount * exchangeRate;
    }
}
