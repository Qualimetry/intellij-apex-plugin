public class FewFields {
    private String name;
    private Integer count;
    private Boolean active;

    public FewFields(String name) {
        this.name = name;
        this.count = 0;
        this.active = true;
    }

    public String getName() { return name; }
    public Integer getCount() { return count; }
    public Boolean isActive() { return active; }
}
