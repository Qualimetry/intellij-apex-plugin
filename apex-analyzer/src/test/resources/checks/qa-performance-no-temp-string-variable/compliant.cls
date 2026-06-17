public class NoTempStringVar {
    public String convertId(Id recordId) {
        return String.valueOf(recordId);
    }

    public String formatAmount(Decimal amount) {
        return String.valueOf(amount);
    }

    public String getLabel(Schema.SObjectField field) {
        return field.getDescribe().getLabel();
    }
}
