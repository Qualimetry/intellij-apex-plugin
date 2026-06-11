public class UnusedField {
    private String usedName;
    private String neverRead;
    private Integer obsoleteCount;
    private Boolean legacyFlag;

    public String getName() {
        return usedName;
    }

    public void setName(String name) {
        this.usedName = name;
    }
}
