public class UnsafeMathService {
    public Decimal calculateAverage(Decimal total, Integer count) {
        return total / count;
    }

    public void processRecords(List<Account> records, Integer batchSize) {
        Integer batches = records.size() / batchSize;
        for (Integer i = 0; i < batches; i++) {
            processBatch(records, i);
        }
    }

    public Integer getRemainder(Integer value, Integer divisor) {
        return value % divisor;
    }

    private void processBatch(List<Account> records, Integer idx) {
        System.debug('Processing batch ' + idx);
    }
}
