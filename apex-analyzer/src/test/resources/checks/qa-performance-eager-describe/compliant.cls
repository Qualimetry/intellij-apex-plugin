public class MetadataServiceCompliant {
    private String targetObject;

    public MetadataServiceCompliant(String targetObject) {
        this.targetObject = targetObject;
    }

    public Schema.DescribeSObjectResult describeAccount() {
        return Account.SObjectType.getDescribe();
    }

    public Boolean isFieldAccessible(String fieldName) {
        Schema.DescribeFieldResult dfr = Account.SObjectType.getDescribe()
            .fields.getMap().get(fieldName).getDescribe();
        return dfr.isAccessible();
    }
}
