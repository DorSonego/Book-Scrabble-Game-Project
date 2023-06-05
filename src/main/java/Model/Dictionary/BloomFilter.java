
package Model.Dictionary;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
public class BloomFilter {
//    int size;
//    BitSet bitSet;
//    MessageDigest [] hashFunctions;
    //constructor
    BitSet bitSet;
    final private int size;
    private String[] algs;

    public BloomFilter(int size , String...algs){
//        this.size = size;
//        bitSet = new BitSet(size);
//        hashFunctions = new MessageDigest[algs.length];
//        int i = 0;
//
//        //adding the hash functions
//        for(String s : algs){
//            try {
//                hashFunctions[i] = MessageDigest.getInstance(s);
//                i++;
//            }
//            catch (NoSuchAlgorithmException e) {
//                throw new RuntimeException("No such algorithm");
//            }
//        }
        this.algs=algs;
        this.size=size;
        bitSet=new BitSet(size);
    }

    public void add(String word){
//        for(MessageDigest hashFunctions : hashFunctions)
//            bitSet.set(getWordBit(word, hashFunctions));
        for(String alg : algs)
            try {
                MessageDigest md = MessageDigest.getInstance(alg);
                byte[] bts=md.digest(word.getBytes());
                BigInteger bi=new BigInteger(bts);
                int val=Math.abs(bi.intValue())%size;
                bitSet.set(val);
            } catch (NoSuchAlgorithmException e) {
            }

    }

    public boolean contains(String word){
//        for(MessageDigest hashFunctions : hashFunctions)
//            if (!bitSet.get(getWordBit(word, hashFunctions)))
//                return false;
//
//        return true;
        for(String str : algs)
            try {
                MessageDigest md = MessageDigest.getInstance(str);
                byte[] bts=md.digest(word.getBytes());
                BigInteger bi=new BigInteger(bts);
                int val=Math.abs(bi.intValue())%size;
                if(!bitSet.get(val))
                    return false;

            } catch (NoSuchAlgorithmException e) {
            }
        return true;
    }

    @Override
    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        for (int i= 0; i <bitSet.length(); i++){
//            if(bitSet.get(i))
//                sb.append("1");
//            else
//                sb.append("0");
//        }
//        return sb.toString();
        StringBuilder sb=new StringBuilder();
        for(int i=0 ; i< bitSet.length() ; i++){
            if(bitSet.get(i)==false)
                sb.append("0");
            else
            sb.append("1");
        }
        return sb.toString();
    }
//    public int getWordBit(String word, MessageDigest hashFunction){
//        byte [] bts = hashFunction.digest(word.getBytes());
//        BigInteger bi = new BigInteger(bts);
//        return Math.abs(bi.intValue()) % size;
//    }
}

