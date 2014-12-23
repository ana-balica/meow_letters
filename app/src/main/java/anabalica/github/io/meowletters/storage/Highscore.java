package anabalica.github.io.meowletters.storage;

/**
 * Highscore model class that allows saving and retrieving data from the database in a consistent
 * way.
 *
 * @author Ana Balica
 */
public class Highscore {
    private String username;
    private int score;

    public Highscore(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
