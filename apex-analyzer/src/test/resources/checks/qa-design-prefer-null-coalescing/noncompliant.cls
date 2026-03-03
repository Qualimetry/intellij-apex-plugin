public class NonCompliant {
    public String getName(String name) {
        return name == null ? 'Unknown' : name;
    }

    public Account getAccount(Account acc) {
        if (acc != null) {
            return acc;
        }
        return new Account();
    }

    public String getDescription(Contact c) {
        String desc = c.Description == null ? 'No description' : c.Description;
        return desc;
    }

    public Integer getCount(Integer val) {
        return val == null ? 0 : val;
    }
}
