public with sharing class StatusResolver {

    private String defaultStatus;

    public StatusResolver() {
        this.defaultStatus = 'Unknown';
    }

    public String resolveStatus(Integer x, Integer y) {
        if (x > 0) {
            return 'X is Positive';
        } else if (y > 0) {
            return 'Y is Positive';
        }
        return defaultStatus;
    }
}
