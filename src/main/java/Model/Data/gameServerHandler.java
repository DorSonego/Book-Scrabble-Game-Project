package Model.Data;

import Model.Logic.BookScrabbleHandler;
import Model.Logic.ClientHandler;
import Model.Logic.DictionaryManager;
import Model.Logic.MyServer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class gameServerHandler  {
    Socket socket;
    Scanner in;
    PrintWriter out;
    BookScrabbleHandler bookHandler;
    DictionaryManager dictionaryManager = DictionaryManager.get();
    MyServer gameServer;
    public gameServerHandler(int port,String IP)
    {

    }

    public boolean challenge(String word)
    {
        in = new Scanner(word);
        out = new PrintWriter(new StringWriter());
        try {
            out=new PrintWriter(socket.getOutputStream());
            in=new Scanner(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dictionaryManager.challenge(word);
    }
    public boolean query(String word)
    {
        return dictionaryManager.query(word);
    }

}
