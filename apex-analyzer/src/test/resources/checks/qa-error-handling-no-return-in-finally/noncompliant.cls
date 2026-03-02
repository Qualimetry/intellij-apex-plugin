public class ResourceManager {
    public Integer processData() {
        Integer result = 0;
        try {
            result = doWork();
        } catch (Exception e) {
            result = -1;
        } finally {
            return result;
        }
    }

    public String readFile() {
        try {
            return fetchData();
        } catch (Exception e) {
            System.debug(e.getMessage());
        } finally {
            return 'default';
        }
    }

    Integer doWork() { return 1; }
    String fetchData() { return ''; }
}
