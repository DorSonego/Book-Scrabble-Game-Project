package Test;


public class Main {
    public static void main(String[] args) {
        HostClientHandlerTest hostClientHandlerTest = new HostClientHandlerTest();
        HostTest hostTest = new HostTest();

        // Run the tests for HostClientHandlerTest
        hostClientHandlerTest.testHandleClient();
        hostClientHandlerTest.testHandleClientChallenge();
        hostClientHandlerTest.testHandleClientAddPlayer();
        hostClientHandlerTest.testHandleClientQuery();
        hostClientHandlerTest.testHandleClientPassTurn();
        hostClientHandlerTest.testHandleClientEndGame();

        // Run the tests for HostTest
        hostTest.testAddPlayer();
        hostTest.testEndGame();
        hostTest.testTryPlaceWord();
        hostTest.testChallenge();
        hostTest.testQuery();
        hostTest.testPassTurn();

        // Print the test results
        System.out.println("HostClientHandlerTest:");
        System.out.println();
        System.out.println("HostTest:");

    }
}

