package Test;
import Model.Connection.hostServer;

import java.io.IOException;
public class gameServerTest {

        public static void main(String[] args) throws IOException {
            hostServer f = new hostServer(9999, "localhost");

            String[][] challengeTests = {{"test1", "word"}, {"test2", "word1"}};
            String[][] queryTests = {{"test1", "word"}, {"test2", "word1"}};

            runTests(f, "Challenge", challengeTests);
            runTests(f, "Query", queryTests);
        }

        public static void runTests(hostServer f, String testName, String[][] testCases) throws IOException {
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
