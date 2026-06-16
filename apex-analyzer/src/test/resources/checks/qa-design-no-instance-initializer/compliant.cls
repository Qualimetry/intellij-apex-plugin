public class DataLoader {
    private List<String> records;
    private Boolean isReady;
    private String label;

    public DataLoader() {
        records = new List<String>();
        isReady = false;
        label = 'default';
    }

    public void load() {
        records.add('Record1');
    }
}
