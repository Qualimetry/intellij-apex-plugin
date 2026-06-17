public class NoDescribeInLoop {
    public void validateFields(List<SObject> records) {
        if (records.isEmpty()) return;
        Schema.DescribeSObjectResult describe = records[0].getSObjectType().getDescribe();
        Map<String, Schema.SObjectField> fields = describe.fields.getMap();
        List<String> fieldNames = new List<String>(fields.keySet());
        Boolean allAccessible = true;
        Integer fieldCount = fieldNames.size();
        if (fieldCount == 0) {
            throw new SecurityException('No fields found');
        }
        if (!fields.get(fieldNames[0]).isAccessible()) {
            allAccessible = false;
        }
    }
}
