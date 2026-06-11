public class NotificationService {
    public String getLabel(Boolean isActive) {
        return isActive ? 'Active' : 'Inactive';
    }

    public String getStatus(Boolean flag) {
        return flag ? 'on' : 'off';
    }

    public Integer getValue(Boolean cond) {
        return cond ? 1 : 0;
    }
}
