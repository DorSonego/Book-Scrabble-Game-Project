package Test;

import Files.Model.Logic.IOSearcher;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TestIOSearcher {
    public static void testIOSearch() throws Exception{
        String words1 = "the quick brown fox \n jumps over the lazy dog";
        String words2 = "A Bloom filter is a space efficient probabilistic data structure, \n conceived by Burton Howard Bloom in 1970";
        PrintWriter out = new PrintWriter(new FileWriter("text1.txt"));
        out.println(words1);
        out.close();
        out = new PrintWriter(new FileWriter("text2.txt"));
        out.println(words2);
        out.close();

        if(!IOSearcher.search("is", "text1.txt","text2.txt"))
            System.out.println("oyur IOsearch did not found a word (-5)");
        if(IOSearcher.search("cat", "text1.txt","text2.txt"))
            System.out.println("your IOsearch found a word that does not exist (-5)");
    }
}
