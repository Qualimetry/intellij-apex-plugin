public class SchemaHelper {
    private String objectName;

    public SchemaHelper(String name) {
        this.objectName = name;
    }

    public String getObjectName() {
        return objectName;
    }

    public Boolean hasField(String fieldName) {
        return objectName != null && fieldName != null;
    }
}
