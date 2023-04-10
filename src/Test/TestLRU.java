package Test;

import Files.Model.Logic.CacheReplacementPolicy;
import Files.Model.Logic.LRU;

public class TestLRU {
    public static void testLRU() {
        CacheReplacementPolicy lru=new LRU();
        lru.add("a");
        lru.add("b");
        lru.add("c");
        lru.add("a");

        if(!lru.remove().equals("b"))
            System.out.println("wrong implementation for LRU (-10)");
    }
}
