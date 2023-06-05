package Model.Dictionary;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {

    Map<String,Dictionary> map;

    DictionaryManager() {
        map=new HashMap<>();
    }

    private static DictionaryManager dm=null;
    public static DictionaryManager get() {
        if(dm==null)
            dm=new DictionaryManager();
        return dm;
    }

    public boolean query(String...args) {
        boolean r=false;
        if(args.length>=2) {

            String qry=args[args.length-1];

            for(int i=0;i<args.length-1;i++) {
                if(!map.containsKey(args[i])) {
                    map.put(args[i], new Dictionary(args[i]));
                }
                Dictionary d = map.get(args[i]);
                r|=d.query(qry);
            }

        }
        return r;
    }

    public boolean challenge(String...args) {
        boolean r=false;
        if(args.length>=2) {

            String qry=args[args.length-1];

            for(int i=0;i<args.length-1;i++) {
                if(!map.containsKey(args[i])) {
                    map.put(args[i], new Dictionary(args[i]));
                }
                Dictionary d = map.get(args[i]);
                r|=d.challenge(qry);
            }

        }
        return r;
    }

    public int getSize() {
        return map.size();
    }
}
