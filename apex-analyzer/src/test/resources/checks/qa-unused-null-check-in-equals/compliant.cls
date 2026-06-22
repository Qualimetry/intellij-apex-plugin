public class GoodEquals {
    private String name;

    public Boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof GoodEquals)) return false;
        GoodEquals other = (GoodEquals) o;
        return this.name == other.name;
    }

    public Integer hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
