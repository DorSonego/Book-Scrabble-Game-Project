package Test;

import Model.Dictionary.Dictionary;

public class TestDictionary {
    public static void testDictionary() {
        Dictionary d = new Dictionary("text1.txt","text2.txt");
        if(!d.query("is"))
            System.out.println("problem with dictionarry in query (-5)");
        if(!d.challenge("lazy"))
            System.out.println("problem with dictionarry in query (-5)");
    }
}
