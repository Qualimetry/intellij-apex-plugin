public class ScoreCalculator {
    private Integer totalScore = 0;
    private Integer penalties = 0;

    public void addPoints(Integer points) {
        totalScore += points;
    }

    public void applyPenalty(Integer amount) {
        penalties += amount;
        totalScore -= amount;
    }

    public Integer getNetScore() {
        return totalScore;
    }
}
