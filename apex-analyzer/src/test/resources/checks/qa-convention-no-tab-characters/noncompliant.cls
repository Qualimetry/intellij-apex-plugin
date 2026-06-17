public class DataProcessor {
	private List<String> records;

	public DataProcessor(List<String> records) {
        this.records = records;
    }

    public void process() {
        for (String r : records) {
			System.debug(r);
        }
    }
}
