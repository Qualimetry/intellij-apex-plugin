public class InvoiceCalculator {
    public Decimal calculateTotal(List<LineItem> items) {
        Decimal taxRate = 0.08;
        Decimal subtotal = 0;

        for (LineItem item : items) {
            subtotal += item.amount;
        }

        return subtotal * (1 + taxRate);
    }

    public Boolean isValid() {
        return true;
    }
}
