public class AggregateCompliant {
    public List<AggregateResult> getStats() {
        return [SELECT Industry, COUNT(Id) c FROM Account GROUP BY Industry];
    }

    public List<AggregateResult> getTotalByStage() {
        return [SELECT StageName, SUM(Amount) total
                FROM Opportunity
                GROUP BY StageName];
    }

    public Integer countAll() {
        List<Account> all = new List<Account>();
        return all.size();
    }
}
