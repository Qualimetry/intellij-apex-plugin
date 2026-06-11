public class FocusedApi {
    private String internalName;
    private Integer internalCount;

    public String getName() { return internalName; }

    public void setName(String name) {
        this.internalName = name;
    }

    public Integer getCount() { return internalCount; }
}
