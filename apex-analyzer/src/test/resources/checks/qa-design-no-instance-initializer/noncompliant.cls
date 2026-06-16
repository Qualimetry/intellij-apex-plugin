public class DataLoader {
    private List<String> records;
    private Boolean isReady;
    {
        records = new List<String>();
    }
    private String label;
    {
        isReady = false;
        label = 'default';
    }
    public void load() {
        records.add('Record1');
    }
}
