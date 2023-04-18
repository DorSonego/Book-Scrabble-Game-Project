//package test;
//import java.io.IOException;
//public class Dictionary {
//    //data members
//    CacheManager cache1;
//    CacheManager cache2;
//    BloomFilter bf;
//    String []fileNamesArray;
//    public Dictionary(String...fileNames) {
//         cache1=new CacheManager(400, new LRU());
//         cache2=new CacheManager(100, new LFU());
//         bf =new BloomFilter(256,"MD5","SHA1");
//         fileNamesArray=fileNames.clone();
//         for(String fileName : fileNamesArray) {
//             bf.add(fileName);
//         }
//    }
//    public boolean query(String word){
//       if(cache1.query(word))
//           return true;
//       else if(cache2.query(word))
//           return false;
//       else if(bf.contains(word))
//           cache1.add(word);
//       cache2.add(word);
//       return true;
//    }
//    public boolean challenge(String word) {
//        try {
//            if(IOSearcher.search(word,fileNamesArray)){
//                cache1.add(word);
//                return true;
//            }
//            cache2.add(word);
//            return false;
//        } catch (IOException e) {
//            return false;
//        }
//    }
//}
package Model.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dictionary {

    CacheReplacementPolicy LRU;
    CacheReplacementPolicy LFU;
    CacheManager cacheForExist;
    CacheManager cacheForNonExist;
    String[] fileNames;
    BloomFilter bf;
    //each fileName is reprasent a book.
    public Dictionary(String... fileNames){
        LRU = new LRU();
        LFU = new LFU();
        this.cacheForExist = new CacheManager(400, new LRU());
        this.cacheForNonExist = new CacheManager(100, new LFU());
        this.bf = new BloomFilter(256, "MD5", "SHA1");
        this.fileNames = fileNames.clone();

        for(String s : fileNames){
            enterWordToBf(s);
        }

    }

    public boolean query(String word){

        if (cacheForExist.query(word))
            return true;

        if(cacheForNonExist.query(word))
            return false;

        if (bf.contains(word)){
            cacheForExist.add(word);
            return true;
        }
        else {
            cacheForNonExist.add(word);
            return false;
        }
    }

    public boolean challenge(String word){
        try {
            if(IOSearcher.search(word, fileNames)){
                cacheForExist.add(word);
                return true;
            }
            else {
                cacheForNonExist.add(word);
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void enterWordToBf(String fileName){
        try {
            Scanner scan = new Scanner(new File(Paths.get(fileName).toUri()));
            while (scan.hasNext()) {
                bf.add(scan.next());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("file not found");
        }
    }
}

