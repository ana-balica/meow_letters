package anabalica.github.io.meowletters.metrics;

/**
 * Level class keeps current user level. User starts from level 1 and advances
 * as much as possible. There is no top limit.
 *
 * @author Ana Balica
 */
public class Level {
    private int level;

    public Level() {
        level = 1;
    }

    public Level(int level) {
        setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws IllegalArgumentException {
        if (level < 1) {
            throw new IllegalArgumentException("Level must be a positive number");
        }
        this.level = level;
    }

    public int updateLevel(int points) {
        if (points > 0) {
            level = (Integer) (points / 100) + 1;
        }
        return level;
    }

    public Level reset() {
        level = 1;
        return this;
    }
}
