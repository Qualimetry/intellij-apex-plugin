public class NotificationService {
    public String getLabel(Boolean isActive) {
        return isActive ? !isActive ? 'Inactive' : 'Active' : 'Unknown';
    }

    public String getStatus(Boolean flag) {
        return flag ? !flag ? 'off' : 'on' : 'n/a';
    }

    public Integer getValue(Boolean cond) {
        return cond ? !cond ? 0 : 1 : -1;
    }
}
