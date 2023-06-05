package Test;
import Model.Connection.Host;
import Model.Data.Tile;
import Model.Data.Word;

class HostTest {

    void testAddPlayer() {
        Host host = Host.getHost();
        int playerID = host.addPlayer("Player1");
        if (playerID == 1) {
            System.out.println("testAddPlayer passed");
        } else {
            System.out.println("testAddPlayer failed");
        }
    }

    void testTryPlaceWord() {
        Host host = Host.getHost();
        host.addPlayer("Player1");
        Word word = new Word(new Tile[5], 1, 1, true);
        int result = host.tryPlaceWord(word);
        if (result == 0) {
            System.out.println("testTryPlaceWord passed");
        } else {
            System.out.println("testTryPlaceWord failed");
        }
    }


    void testChallenge() {
        Host host = Host.getHost();
        host.addPlayer("Player1");
        boolean result = host.challenge("WORD");
        if (!result) {
            System.out.println("testChallenge passed");
        } else {
            System.out.println("testChallenge failed");
        }
    }


    void testQuery() {
        Host host = Host.getHost();
        host.addPlayer("Player1");
        boolean result = host.query("WORD");
        if (!result) {
            System.out.println("testQuery passed");
        } else {
            System.out.println("testQuery failed");
        }
    }


    void testPassTurn() {
        Host host = Host.getHost();
        host.addPlayer("Player1");
        host.addPlayer("Player2");
        int turn = host.passTurn(0);
        if (turn == 1) {
            System.out.println("testPassTurn passed");
        } else {
            System.out.println("testPassTurn failed");
        }
    }


    void testEndGame() {
        Host host = Host.getHost();
        host.addPlayer("Player1");
        host.endGame();
        if (host.flag) {
            System.out.println("testEndGame passed");
        } else {
            System.out.println("testEndGame failed");
        }
    }
}


