package Test;


import Model.Data.hostClientHandler;
import java.lang.Object;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

    class HostClientHandlerTest {

        public void testHandleClient() {
            String input = "tryPlaceWord-1-TESTWORD-1-1-vertical";
            String expectedOutput = "1";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        public void testHandleClientChallenge() {
            String input = "challenge-WORD";
            String expectedOutput = "false";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        public void testHandleClientAddPlayer() {
            String input = "addPlayer-Player1";
            String expectedOutput = "1";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        public void testHandleClientQuery() {
            String input = "query-WORD";
            String expectedOutput = "false";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        public void testHandleClientPassTurn() {
            String input = "passTurn-0";
            String expectedOutput = "1";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        public void testHandleClientEndGame() {
            String input = "endGame";
            String expectedOutput = "game over";
            String output = testClientHandler(input);
            if (expectedOutput.equals(output)) {
                System.out.println("Test Passed!");
            } else {
                System.out.println("Test Failed!");
            }
        }

        private String testClientHandler(String input) {
            InputStream inputStream = new ByteArrayInputStream(input.getBytes());
            OutputStream outputStream = new ByteArrayOutputStream();
            hostClientHandler clientHandler = new hostClientHandler();
            clientHandler.handleClient(inputStream, outputStream);
            return outputStream.toString().trim();
        }
    }
