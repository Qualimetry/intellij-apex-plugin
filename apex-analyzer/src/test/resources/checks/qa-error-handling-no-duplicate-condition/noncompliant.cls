public with sharing class StatusResolver {

    private String defaultStatus;

    public StatusResolver() {
        this.defaultStatus = 'Unknown';
    }

    public String resolveStatus(Integer x) {
        if (x > 0)
            return 'Positive';
        else if (x > 0)
            return 'Also Positive';
        return defaultStatus;
    }
}
