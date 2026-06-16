public class Compliant {
    public String getName(String name) {
        return name != null ? name : 'Unknown';
    }

    public String getLabel(Account acc) {
        String label = acc.Name;
        if (label == null) {
            label = 'Default';
        }
        return label;
    }

    // name == null ? 'default' : name
    public String getStatus(Opportunity opp) {
        return opp.StageName ?? 'New';
    }

    public Integer getQuantity(Integer qty) {
        return qty != null ? qty : 0;
    }
}
