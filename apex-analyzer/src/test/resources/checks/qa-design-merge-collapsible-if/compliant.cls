public class FilterService {
    public void applyFilters(Boolean isActive, Boolean isVerified) {
        if (isActive && isVerified) {
            processVerifiedRecord();
        }
    }

    public void applyAlternate(Boolean a) {
        if (a) {
            processFallback();
        }
    }

    private void processVerifiedRecord() { }
    private void processFallback() { }
}
