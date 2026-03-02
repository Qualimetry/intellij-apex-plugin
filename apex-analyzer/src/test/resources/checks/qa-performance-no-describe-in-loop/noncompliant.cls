public class DescribeInLoop {
    public void validateFields(List<SObject> records) {
        for (SObject rec : records) {
            Schema.DescribeSObjectResult describe = rec.getSObjectType().getDescribe();
            Map<String, Schema.SObjectField> fields = describe.fields.getMap();
            for (String fieldName : fields.keySet()) {
                Schema.DescribeFieldResult fr = fields.get(fieldName).getDescribe();
                if (!fr.isAccessible()) {
                    throw new SecurityException('No access to ' + fieldName);
                }
            }
        }
    }
}
