package Model.Data;

import Model.Logic.ClientHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static java.lang.System.exit;

public class Guest implements Observable {


    Socket hostSocket;
    List<Tile> myTiles;
    int id;
    boolean stop=false;
    Board myBoard;
    InputStream in;
    OutputStream out;
    Scanner scanner;
    PrintWriter pw;
    player currentPlayer;
    String name;

    Thread thread ;
    public Guest(int id)
    {
        this.id=id;

    }


    public void connectToHost(int port,String ip)
    {
        try {
            hostSocket = new Socket(ip,port);
        }
        catch (IOException e)
        {
            throw new RuntimeException("unable to connect to Host");
        }
    }
    public void tryPlaceWord(String word,int row,int coulmn,String direction)
    {
        String msg= "tryPlaceWord-"+id+"-"+word+"-"+row+"-"+coulmn+"-"+direction;
        int score = Integer.parseInt(sendRequest(msg));
    }
    public void tryChallenge()
    {

    }
    public void tryQuery(){}
    public void passTheTurn(){}
    public void addPlayer(){


    }
    public void endGame(){

    }

    public String sendRequest(String s)
    {
        try {
            scanner= new Scanner(hostSocket.getInputStream());
            pw=new PrintWriter(hostSocket.getOutputStream());
        }
        catch (Exception e){e.printStackTrace();};
        pw.println(s);
        pw.flush();
        String ans =scanner.next();
        return ans;

        //need to see what to do with answer
    }


    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
    public void listener()
    {
        while(!stop)
        {
            try {
                pw=new PrintWriter(hostSocket.getOutputStream());
                InputStream inputStream=hostSocket.getInputStream();
                if(inputStream.read()!=-1)
                {
                    exit(0);
                }
            }
            catch (Exception e){e.printStackTrace();};
        }
    }
}
