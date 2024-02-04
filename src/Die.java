
import java.util.Random;

/**
 * Die class represents a six-sided die used in a game.
 */
public class Die {

    /**
     * Default constructor for the Die class.
     */
    public Die() {
    }

    /**
     * Rolls die and calculates score based on provided faces (numbers).
     *
     * @param faces Array representing the current faces of die.
     *
     * @return The total score after rolling die.
     */
    public int roll(int[] faces) {

        Random randomNum = new Random();
        int score = 0;
                for (int i = 0; i < faces.length; i++) {
                    if (faces[i] != 2 && faces[i] != 5) {
                        faces[i] = randomNum.nextInt(6) + 1;
                    }
                    if (faces[i] != 2 && faces[i] != 5) {
                        score += faces[i];
                    }
                }
    return score;
    }

    /**
     * Checks if all faces are either 2 or 5 (die is stuck).
     *
     * @param faces Array representing the current faces of  die.
     *
     * @return True if the die is stuck, false if unstuck.
     */
    public boolean stuck(int[] faces) {
        for (int element : faces) {
            if (element != 2 && element != 5) {
                return false;
            }
        }
        return true;
    }
}
