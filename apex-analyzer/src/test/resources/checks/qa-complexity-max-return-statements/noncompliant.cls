public class ManyReturns {
    public String categorize(Integer score) {
        if (score >= 90) return 'Excellent';
        if (score >= 80) return 'Good';
        if (score >= 70) return 'Average';
        if (score >= 60) return 'Below Average';
        if (score >= 50) return 'Poor';
        if (score >= 40) return 'Very Poor';
        if (score >= 30) return 'Critical';
        if (score >= 20) return 'Failing';
        if (score >= 10) return 'Minimal';
        return 'None';
    }
}
