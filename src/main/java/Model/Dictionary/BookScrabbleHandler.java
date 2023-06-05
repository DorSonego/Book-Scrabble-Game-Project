package Model.Dictionary;
import Model.Connection.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.io.PrintWriter;

public class BookScrabbleHandler implements ClientHandler {
    String lineO;

    String[] words;

    @Override
    public void handleClient(InputStream inFromclient , OutputStream outToClient) {
        Scanner scanner = new Scanner(inFromclient);
        PrintWriter out = new PrintWriter(outToClient);
        try {
            if(scanner.hasNextLine()){
                lineO = scanner.nextLine();
                words = lineO.split(",");
            }
        } catch (Exception e){e.printStackTrace();}
        DictionaryManager dM = new DictionaryManager();

        String[] words2 = new String[words.length-1];
        for(int i =0 , s=1; s<words.length; i++ , s++){
            words2[i] = words[s];
        }
//
        boolean flag;
        if(words[0].equals("C")){
            flag = dM.challenge(words2);
        }
        else {
            flag = dM.query(words2);
        }

        if(flag){
            out.println("true");
            out.flush();
        }
        else {
            out.println("false");
            out.flush();
        }
        out.close();
    }

    @Override
    public void close() {}
}
