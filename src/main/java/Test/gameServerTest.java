package Test;
import Model.Data.gameServer;

import java.io.IOException;
public class gameServerTest {

        public static void main(String[] args) throws IOException {
            gameServer f = new gameServer(9999, "localhost");

            String[][] challengeTests = {{"test1", "word1"}, {"test2", "word2"}};
            String[][] queryTests = {{"test1", "word1"}, {"test2", "word2"}};

            runTests(f, "Challenge", challengeTests);
            runTests(f, "Query", queryTests);
        }

        public static void runTests(gameServer f, String testName, String[][] testCases) throws IOException {
            for (String[] testCase : testCases) {
                String testCaseName = testCase[0];
                String expectedOutput = testCase[1];

                boolean result;
                if (testName.equals("Challenge")) {
                    result = f.challenge(new String[]{testCaseName}, expectedOutput);
                } else {
                    result = f.query(new String[]{testCaseName}, expectedOutput);
                }

                if (result) {
                    System.out.println(testName + " " + testCaseName + " passed");
                } else {
                    System.out.println(testName + " " + testCaseName + " failed");
                }
            }
        }
    }
