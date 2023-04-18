package Model.Logic;
import java.util.LinkedHashSet;
public class LRU implements CacheReplacementPolicy {
    private int size;
    private LinkedHashSet <String> cache;
    public LRU(){
        this.size = size;
        this.cache = new LinkedHashSet<>(size);
    }
    @Override
    public void add(String word){
        this.cache.remove(word);
    this.cache.add(word);
    }
    @Override
    public String remove(){
        String[] cacheArray = cache.toArray(new String[cache.size()]);
        cache.remove(cacheArray[0]);
        return cacheArray[0];
    }
}

