import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * A simple game called "Stuck In The Mud" where players roll dice in rounds, with a score
 * of 100 being declared the winner.
 *
 */
public class StuckInTheMud {

    /** The list of players participating in the game. */
    public static CircularLinkedList<Player> players;

    /** The number of dice used in each roll. */
    public static final int NUM_DICE = 4;

    /** Winning score of the game. */
    public static final int WINNING_SCORE = 100;

    /**
     * The main method that initiates the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        players = new CircularLinkedList<>();

        addPlayer("Kidus", 0);
        addPlayer("Batman", 0);
        addPlayer("Bond, James", 0);
        addPlayer("Anakin Skywalker", 0);
        players.remove(2);


        System.out.println();
        System.out.println();
        System.out.println(".............Lets Play!..............");
        System.out.println("5 .... 4 .... 3 .... 2 .... 1 .... GO!");
        System.out.println();
        System.out.println();
        playSound("src/countdown.wav");
        startGame(NUM_DICE);
    }


    /**
     * Adds a new player to the game with given name and initial score.
     *
     * @param name  The name of player.
     * @param score The initial score of player.
     */
    public static void addPlayer(String name, int score) {
        players.add(new Player(name, score));
    }

    /**
     * Initiates a round of the game
     * Each player takes turns rolling dice until one reaches a score of 100.
     *
     * @param NUM_DICE The number of dice used in each roll.
     */
    public static void startGame(int NUM_DICE) {

        Die die = new Die();
        Random randomNum = new Random();

        Iterator<Player> playerIterator = players.iterator();

        Player startingPlayer = null;

        if (playerIterator.next() != null) {
            for (int i = 0; i < randomNum.nextInt(players.getSize()) + 1; i++) {
                startingPlayer = playerIterator.next();
            }
        }

        int roundNumber = 1;
        System.out.println(".........Round " + roundNumber + ".........");
        playSound("src/round.wav");
        while (playerIterator.hasNext()) {
            Player currentPlayer = playerIterator.next();

            int score = currentPlayer.score;
            int[] faces = new int[NUM_DICE];

            playSound("src/roll.wav");
            do {
                score += die.roll(faces);
                System.out.println(Arrays.toString(faces));
            } while (!die.stuck(faces));

            currentPlayer.setScore(score);

            System.out.println(currentPlayer.name + "'s new score: " + currentPlayer.score);
            System.out.println();

            if (currentPlayer.score >= WINNING_SCORE) {

                System.out.println();
                System.out.println("We have a winner: " + currentPlayer.name +
                        ", with a score of " + currentPlayer.score + "!");
                playSound("src/winner.wav");
                return;
            }

            if (currentPlayer == startingPlayer) {
                System.out.println("No winner, moving on to next round!");
                roundNumber++;
                System.out.println(".........Round " + roundNumber + ".........");
                System.out.println();
                playSound("src/round.wav");
            }
        }
    }


    /**
     * Plays sound from specified file path.
     *
     * @param soundFilePath The file path of the sound file.
     */
    private static void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}