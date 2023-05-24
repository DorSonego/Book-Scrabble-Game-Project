package Model.Data;

import Model.Logic.*;
import Model.Logic.Dictionary;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Host extends Observable  {
    Board myBoard;
    static Host host;
    boolean flag = false;
    int id=0;int currentTurn=0;
    List<player> players=new ArrayList<>();
    Tile.Bag bag;
    ScoreBoard scoreBoard;
    //add socket to game server
    Socket gamesocket;
    int myPort;
    int gamePort;
    String gameServerIP;
    MyServer myServer=new MyServer(myPort,new hostClientHandler());

    public Host()
    {
        this.myBoard=Board.getBoard();
        this.bag=Tile.Bag.getBag();
        this.scoreBoard=new ScoreBoard();
    }
    public Socket tryOpenPort()
    {
        try {
            gamesocket=new Socket(gameServerIP,gamePort);
        }
        catch (Exception e){e.printStackTrace();};
        return gamesocket;
    }
    public int addPlayer(String name)
    {
        players.add(new player(name,++id));
        return id;
    }
    public static Host getHost()
    {
        if (host==null)
            host=new Host();
        return host;
    }
    public void endGame(){
        myServer.close();
        flag = true;
    }
    public int tryPlaceWord(Word word)
    {
        int result= myBoard.tryPlaceWord(word);
        if (result > 0);
        //add a-lot


        return result;
    }
    public boolean C_and_Q(String type,String word)
    {
        try {
            Scanner scanner = new Scanner(gamesocket.getInputStream());
            PrintWriter printWriter=new PrintWriter(gamesocket.getOutputStream());
            if(type.equals("challenge"))
                printWriter.println("C,"+word);

            else
                printWriter.println("Q,"+word);
            printWriter.flush();
            if(scanner.next().equals("false"))
                return false;
            return true;
        }catch (Exception e){e.printStackTrace();};
        return false;
    }
    public boolean challenge(String word)
    {
        boolean ans=C_and_Q("challenge",word);
        return ans;
    }
    public boolean query(String word)
    {
        boolean ans=C_and_Q("query",word);
        return ans;
    }

    public int passTurn(int turn)
    {
        currentTurn=turn=(turn+1)%players.size();
        return currentTurn;

    }


//    public String convertWordToString(Word word)
//    {
//        StringBuilder sb=new StringBuilder();
//        for (Tile tile:word.tileWord) {
//            sb.append(tile.getLetter());
//        }
//        return sb.toString();
//    }


}
