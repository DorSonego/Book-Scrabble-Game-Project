//package Files.Model.Logic;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//public class IOSearcher {
//    public static boolean search(String word, String... filesNames) throws IOException {
//        for (String fileName : filesNames) {
//            BufferedReader reader = new BufferedReader(new FileReader(fileName));
//            String line;
//            int lineNumber=0;
//            while ((line = reader.readLine())!= null) {
//                lineNumber++;
//                if (line.contains(word)) {
//                    reader.close();
//                    return true;
//            }
//        }
//    }
//        return false;
//}
//
//}

package Files.Model.Logic;;

        import java.io.File;
        import java.util.Scanner;

public class IOSearcher {

    public static boolean search(String word, String...fileNames) throws Exception{
        boolean found=false;
        for(int i=0;i<fileNames.length && !found; i++) {
            Scanner s=new Scanner(new File(fileNames[i]));
            while(s.hasNext() && !found)
                if(s.next().equals(word))
                    found=true;
            s.close();
        }
        return found;
    }
}

