package Model.Data;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Guest implements IPlayer  {


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

    @Override

    public void tryPlaceWord(String word,int row,int coulmn,String direction)
    {
        String msg= "tryPlaceWord-"+id+"-"+word+"-"+row+"-"+coulmn+"-"+direction;
        int score = Integer.parseInt(sendRequest(msg));
    }
    @Override
    public boolean challenge(String word)
    {
        String msg= "challenge-"+id+"-"+word;
        return Boolean.parseBoolean(sendRequest(msg));

    }


    public void endGame(){
        stop=true;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public String sendRequest(String s)
    {
        writer.println(s);
        writer.flush();
        return reader.nextLine();

        //need to see what to do with answer
    }
    public String[][] convertBoardFromString(String string_board) {
        String[] rows = string_board.trim().split("\n");
        String[][] tempBoard = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            tempBoard[i] = rows[i].trim().split("");
        }
        board =tempBoard;
        return board;
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
                    if (!(msg.equals("all-endGame")))
                    {
                        endGame();
                    }
                    else{
                    convertBoardFromString(stringBuilder.toString());
                }
                }
            }
            catch (Exception e){e.printStackTrace();};
        }
    }

    @Override
    public int passTurn(int turn) {
        this.sendRequest("passTurn");
        return turn+1;
    }

    @Override
    public Tile[] getHand() {
        return new Tile[0];
    }

    @Override
    public void drawTile() {
        this.myTiles.add(Tile.Bag.getBag().getRand());
    }
}
