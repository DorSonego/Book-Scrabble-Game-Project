package Test;

import Model.Data.Board;
import Model.Connection.Guest;
import Model.Data.Tile;
import Model.Connection.player;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class GuestTest implements Observable {
    private Socket hostSocket;
    private List<Tile> myTiles;
    private int id;
    private boolean stop = false;
    private Board myBoard;
    private InputStream in;
    private OutputStream out;
    private Scanner scanner;
    private PrintWriter pw;
    private player currentPlayer;
    private String name;

    private Thread thread;

    public void Guest(int id) {
        this.id = id;
    }

    public void connectToHost(int port, String ip) {
        try {
            hostSocket = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException("Unable to connect to Host");
        }
    }

    public void tryPlaceWord(String word, int row, int column, String direction) {
        String msg = "tryPlaceWord-" + id + "-" + word + "-" + row + "-" + column + "-" + direction;
        int score = Integer.parseInt(sendRequest(msg));
    }

    public void tryChallenge() {
        // Get the word to challenge
        String word = "CHALLENGEWORD";

        // Send the challenge request to the server
        String response = sendRequest("challenge-" + word);

        // Process the server response
        if (response.equals("true")) {
            System.out.println("Challenge successful: The word \"" + word + "\" is valid.");
        } else {
            System.out.println("Challenge unsuccessful: The word \"" + word + "\" is invalid.");
        }
    }


    public void tryQuery() {

    }


    public void passTheTurn() {
        // Implementation for passTheTurn()
    }

    public void addPlayer() {
        // Implementation for addPlayer()
    }

    public void endGame() {
        // Implementation for endGame()
    }

    public String sendRequest(String s) {
        try {
            scanner = new Scanner(hostSocket.getInputStream());
            pw = new PrintWriter(hostSocket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.println(s);
        pw.flush();
        String ans = scanner.next();
        return ans;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {
        // Implementation for addListener()
    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {
        // Implementation for removeListener()
    }

    public void listener() {
        while (!stop) {
            try {
                pw = new PrintWriter(hostSocket.getOutputStream());
                InputStream inputStream = hostSocket.getInputStream();
                if (inputStream.read() != -1) {
                    exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



        public void testTryPlaceWord() {
            String word = "TESTWORD";
            int row = 1;
            int column = 1;
            String direction = "vertical";

            try {
                // Perform the tryPlaceWord operation
                tryPlaceWord(word, row, column, direction);

                // Print the result
                System.out.println("tryPlaceWord test:");
                System.out.println("Word: " + word);
                System.out.println("Row: " + row);
                System.out.println("Column: " + column);
                System.out.println("Direction: " + direction);
                System.out.println("Operation successful.");
            } catch (Exception e) {
                // Print the error message
                System.out.println("tryPlaceWord test failed: " + e.getMessage());
            }
        }


    public void testTryChallenge() {
        String word = "CHALLENGEWORD";

        try {
            // Perform the tryChallenge operation
            tryChallenge();

            // Print the result
            System.out.println("tryChallenge test:");
            System.out.println("Word: " + word);
            System.out.println("Operation successful.");
        } catch (Exception e) {
            // Print the error message
            System.out.println("tryChallenge test failed: " + e.getMessage());
        }
    }


    public void testTryQuery() {
        String word = "QUERYWORD";

        try {
            // Perform the tryQuery operation
            tryQuery();

            // Print the result
            System.out.println("tryQuery test:");
            System.out.println("Word: " + word);
            System.out.println("Operation successful.");
        } catch (Exception e) {
            // Print the error message
            System.out.println("tryQuery test failed: " + e.getMessage());
        }
    }


    public void testPassTheTurn() {
        try {
            // Perform the passTheTurn operation
            passTheTurn();

            // Print the result
            System.out.println("passTheTurn test:");
            System.out.println("Turn passed successfully.");
        } catch (Exception e) {
            // Print the error message
            System.out.println("passTheTurn test failed: " + e.getMessage());
        }
    }


    public void testAddPlayer() {
        String playerName = "Player1";

        try {
            // Perform the addPlayer operation
            addPlayer();

            // Print the result
            System.out.println("addPlayer test:");
            System.out.println("Player name: " + playerName);
            System.out.println("Operation successful.");
        } catch (Exception e) {
            // Print the error message
            System.out.println("addPlayer test failed: " + e.getMessage());
        }
    }


    public void testEndGame() {
        try {
            // Perform the endGame operation
            endGame();

            // Print the result
            System.out.println("endGame test:");
            System.out.println("Game ended successfully.");
        } catch (Exception e) {
            // Print the error message
            System.out.println("endGame test failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create an instance of Guest
        Guest guest = new Guest(123);

        // Connect to the host
        guest.connectToHost(1234, "127.0.0.1");


    }
}

