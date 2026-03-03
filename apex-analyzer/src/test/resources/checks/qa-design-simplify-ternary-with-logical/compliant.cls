public class Compliant {
    public Boolean isActive(Boolean flag) {
        return flag;
    }

    public String getLabel(Boolean active) {
        return active ? 'Yes' : 'No';
    }

    public Boolean check(Integer x) {
        return x > 0;
    }

    public Integer getVal(Boolean flag) {
        return flag ? 1 : 0;
    }

    public Boolean negate(Boolean b) {
        return !b;
    }
}
