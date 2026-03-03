public class Compliant {
    public String fromInteger(Integer n) {
        return String.valueOf(n);
    }

    public String fromDecimal(Decimal amount) {
        return String.valueOf(amount);
    }

    public String fromBoolean(Boolean isActive) {
        return String.valueOf(isActive);
    }

    public String fromLong(Long counter) {
        return String.valueOf(counter);
    }

    public String concat(String a, String b) {
        return a + b;
    }
}
