public class NonCompliantNullHandler {
    public Boolean isValid(String value) {
        if (value.equals(null)) {
            return false;
        }
        return value.length() > 0;
    }

    public String safeLookup(Map<String, String> data, String key) {
        String result = data.get(key);
        if (result.equals( null )) {
            return 'N/A';
        }
        return result;
    }

    public Boolean hasName(Account acct) {
        return !acct.Name.equals(null);
    }
}
