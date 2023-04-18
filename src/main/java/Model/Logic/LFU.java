package Model.Logic;
import java.util.LinkedHashMap;
import java.util.Map;
public class LFU implements CacheReplacementPolicy {
    private int size;
    private LinkedHashMap<String, Integer> cache;
    String save;
    public LFU() {
        cache = new LinkedHashMap<>();
        save = null;
    }
    public void add(String word) {
        int Freuency = 1;
        if (!cache.containsKey(word)) {
            if (cache.isEmpty())
                save = word;
            cache.put(word, Freuency);
        } else cache.put(word, ++Freuency);
    }
    @Override
    public String remove() {
        int minVal = cache.get(save);
        for (Map.Entry<String, Integer> entry : cache.entrySet()) {
            if (minVal > entry.getValue()) {
                minVal = entry.getValue();
                save = entry.getKey();
            }
        }
        return save;
    }
}
