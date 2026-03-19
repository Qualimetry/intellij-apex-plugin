public class AggregateBad {
    public AggregateResult getStats() {
        List<AggregateResult> results = [SELECT COUNT(Id) c FROM Account];
        return results[0];
    }

    public Decimal getTotalAmount() {
        AggregateResult ar = [SELECT SUM(Amount) total FROM Opportunity];
        return (Decimal) ar.get('total');
    }

    public Integer countByStatus() {
        AggregateResult ar = [SELECT AVG(Amount) avg FROM Opportunity];
        return (Integer) ar.get('avg');
    }
}
