public class ForBracesCompliant {
    private List<String> items;

    public void processItems() {
        items = new List<String>();
        for (Integer i = 0; i < 10; i++) {
            items.add('Item ' + i);
        }
    }

    public Integer getCount() {
        return items.size();
    }
}
