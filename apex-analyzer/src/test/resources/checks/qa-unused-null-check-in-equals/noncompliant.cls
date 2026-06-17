public class BadEquals {
    private String name;

    public Boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        BadEquals other = (BadEquals) o;
        if (other == null) return false;
        return this.name == other.name;
    }

    public Integer hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
