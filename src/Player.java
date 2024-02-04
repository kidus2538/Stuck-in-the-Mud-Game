/**
 * Player class represents a participant of a game with name and score.
 */
public class Player {
    /**
     * The name of the player.
     */
    public String name;

    /**
     * The score of the player.
     */
    public int score;

    /**
     * Constructs a new player with given name with score set to 0.
     *
     * @param name  The name of player.
     * @param score The initial score of the player (set to 0).
     */
    public Player(String name, int score) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Sets the score of player to specified value.
     *
     * @param score The new score of player.
     */
    public void setScore(int score) {

        this.score = score;
    }

    /**
     * Resets the score of player to 0.
     *
     */
    public void resetScore() {
        this.score = 0;
    }
}