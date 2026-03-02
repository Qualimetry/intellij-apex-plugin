public class NonCompliant {
    public Boolean isActive(Boolean flag) {
        return flag ? true : false;
    }

    public Boolean check(Integer x, Integer y) {
        Boolean result = (x > y) ? false : true;
    }

    public void process(String name) {
        Boolean hasName = name != null ? true : false;
        if (hasName) {
            System.debug(name);
        }
    }

    public String getLabel(Boolean b) {
        return b ? 'Active' : 'Inactive';
    }
}
