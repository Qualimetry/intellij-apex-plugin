public class MetadataService {
    private String targetObject;

    public MetadataService(String targetObject) {
        this.targetObject = targetObject;
    }

    public List<String> getAllObjectNames() {
        Map<String, Schema.SObjectType> gd = Schema.getGlobalDescribe();
        List<String> names = new List<String>();
        for (String key : gd.keySet()) {
            names.add(key);
        }
        return names;
    }
}
