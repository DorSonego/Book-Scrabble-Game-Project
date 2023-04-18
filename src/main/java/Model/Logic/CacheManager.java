package Model.Logic;
import java.util.LinkedHashSet;

public class CacheManager {
	int cacheSize;
	private LinkedHashSet<String> cacheWords;
	CacheReplacementPolicy crp;
    public CacheManager(int  cacheSize,CacheReplacementPolicy crp) {
		this.cacheSize = cacheSize;
		this.crp = crp;
		this.cacheWords = new LinkedHashSet<String>();

	}

public boolean query(String word) {
	if (cacheWords.contains(word))
		return true;
	else
		return false;
}
public void add(String word) {//update crp and add to cache words
		crp.add(word);
		cacheWords.add(word);
        if (cacheWords.size() > cacheSize) {
           cacheWords.remove(crp.remove());
        }
}

}
