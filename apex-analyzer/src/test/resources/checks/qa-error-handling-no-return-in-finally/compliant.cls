public class ResourceManager {
    public Integer processData() {
        Integer result = 0;
        try {
            result = doWork();
            return result;
        } catch (Exception e) {
            System.debug(e.getMessage());
            return -1;
        } finally {
            cleanup();
        }
    }

    public String readFile() {
        String data = '';
        try {
            data = fetchData();
        } finally {
            closeConnection();
            System.debug('Done');
        }
        return data;
    }

    Integer doWork() { return 1; }
    String fetchData() { return ''; }
    void cleanup() { }
    void closeConnection() { }
}
