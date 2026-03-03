public class Schema {
    private String objectName;

    public Schema(String name) {
        this.objectName = name;
    }

    public String getObjectName() {
        return objectName;
    }

    public Boolean hasField(String fieldName) {
        return objectName != null && fieldName != null;
    }
}
