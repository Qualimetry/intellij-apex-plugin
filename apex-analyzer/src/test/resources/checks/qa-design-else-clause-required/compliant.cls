public class ElseClauseCompliant {
    private String status;

    public void categorize(Integer score) {
        if (score >= 90) {
            status = 'Excellent';
        } else if (score >= 70) {
            status = 'Good';
        } else {
            status = 'Needs Improvement';
        }
    }
}
