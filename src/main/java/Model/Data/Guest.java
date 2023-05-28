package Model.Data;

import Model.Logic.ClientHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.*;
import java.net.ServerSocket;
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

public class Guest  {


    Socket serverConnection;
    //may need change
    String[][] board=new String[15][15];
    List<Tile> myTiles;
    int id;
    int port;
    String ip;
    boolean stop=false;
    InputStream in;
    PrintWriter out;
    Scanner reader;
    PrintWriter writer;
    player currentPlayer;
    String name;

    Thread thread ;
    public Guest(int id)
    {
        this.id=id;
        serverConnection = null;
        writer = null;
        reader = null;
        // add user input
        System.out.println("plz enter ip");
        try {
            Scanner s = new Scanner(System.in);
            ip=s.nextLine();
            System.out.println("plz enter port");
            s = new Scanner(System.in);
            port=Integer.parseInt(s.nextLine());
        }
        catch (Exception e){e.printStackTrace();}
        connectToHost(port,ip);

        startThread();
    }
    public void startThread()
    {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                listener();
            }
        });
    }
    public void stopThread()
    {
        stop=true;
    }
    public void connectToHost(int port,String ip)
    {
        try {
            serverConnection = new Socket(ip,port);
            reader= new Scanner(serverConnection.getInputStream());
            writer=new PrintWriter(serverConnection.getOutputStream());
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


//    public void passTheTurn(){}
//    public void addPlayer(){
//
//
//    }
//    public void endGame(){
//
//    }

    public String sendRequest(String s)
    {
        writer.println(s);
        writer.flush();
        String ans = reader.nextLine();
        return ans;

        //need to see what to do with answer
    }
    public String[][] convertBoardFromString(String string_board)
    {
        //create function that convert string of board to 2d array
    }


    public void listener()
    {
        while(!stop)
        {
            try {
                StringBuilder stringBuilder=new StringBuilder();
                out=new PrintWriter(serverConnection.getOutputStream());
                Scanner scanner=new Scanner(serverConnection.getInputStream());
                in=serverConnection.getInputStream();
                int b=in.read();
                if(b!=-1)
                {
                    stringBuilder.append(b);
                    stringBuilder.append(scanner.nextLine());
                    String msg =stringBuilder.toString();
                    if (!(msg.equals("stop")))
                    {
                        stopThread();
                    }
                    else{
                    convertBoardFromString(stringBuilder.toString());
                }
                }
            }
            catch (Exception e){e.printStackTrace();};
        }
    }
}
