package Test;



import static Test.TestBag.testBag;
import static Test.TestBloomFilter.testBloomFilter;
import static Test.TestBoard.testBoard;
import static Test.TestBookScrabbleHandler.testBSCH;
import static Test.TestCacheManager.testCacheManager;
import static Test.TestDictionary.testDictionary;
import static Test.TestDictionaryManager.testDM;
import static Test.TestIOSearcher.testIOSearch;
import static Test.TestLFU.testLFU;
import static Test.TestLRU.testLRU;
import static Test.TestMyServer.testServer;

public class MainTrain {


	public static void main(String[] args) {
		testBag(); // 30 points
		testBoard(); // 70 points
		System.out.println("done");

		testLRU();
		testLFU();
		testCacheManager();
		testBloomFilter();
		try {
			testIOSearch();
		} catch(Exception e) {
			System.out.println("you got some exception (-10)");
		}
		testDictionary();
		System.out.println("done");

		if(testServer()) {
			testDM();
			testBSCH();
		}
		System.out.println("done");
	}

}
