package anabalica.github.io.meowletters.metrics;

/**
 * Score class keeps the current user score. User starts from score 0 and keeps
 * gaining more for each correct move. There is no top limit.
 *
 * @author Ana Balica
 */
public class Score {
    int points;

    public Score() {
        points = 0;
    }

    public Score(int points) {
        setPoints(points);
    }

    public void setPoints(int points) throws IllegalArgumentException {
        if (points < 0) {
            throw new IllegalArgumentException("Number of points must be a positive number");
        }
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    /**
     * Add points to the score.
     *
     * @param points int positive amount of points
     * @return int total points
     */
    public int addPoints(int points) {
        if (points > 0) {
            this.points += points;
        }
        return this.points;
    }

    /**
     * Update the score according to the size of the letter chain.
     *
     * @param chainSize int size of letter chain
     * @return int total points
     */
    public int update(int chainSize) {
        if (chainSize > 1) {
            points += (chainSize - 1) * 10;
        }
        return points;
    }

    /**
     * Reset the score.
     */
    public Score reset() {
        points = 0;
        return this;
    }
}
