package Model.Data;

import Model.Logic.*;
import Model.Logic.Dictionary;

import java.io.*;
import java.net.Socket;
import java.util.*;




public class Host extends Observable implements IPlayer {
    //need to identify between notify specific player vs notify all
    Board myBoard;
    static Host host;
    public boolean flag = false;
    int id=0;
    int currentTurn=0;
    List<player> players=new ArrayList<>();
    Tile.Bag bag;
    //need init
    List<Tile>myTiles;

    Socket gamesocket;
    int myPort;
    int gamePort;
    String gameServerIP;
    MyServer myServer;
    hostClientHandler myHostClientHandler;

    public Host()
    {
        this.myBoard=Board.getBoard();
        this.bag=Tile.Bag.getBag();
        this.myPort=8000;
        myHostClientHandler = new hostClientHandler();
        myServer=new MyServer(myPort,myHostClientHandler);
//        this.scoreBoard=new ScoreBoard();
    }
    public String BoardToString()
    {
        StringBuilder sb=new StringBuilder();
        Tile[][]arr= myBoard.getTile();
        for (Tile[] t:arr) {
            for (Tile tile :t) {
                sb.append(tile.letter);
                sb.append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public Socket tryOpenPort()
    {
        try {
            gamesocket=new Socket(gameServerIP,gamePort);
        }
        catch (Exception e){e.printStackTrace();}
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
    public void endGame(){                //send massage "all-endGame" //need to close threads in guests

        myServer.close();
        flag = true;
        String s = "all-endGame";
        s.notifyAll();
        try {
            PrintWriter outEndGame=new PrintWriter(gamesocket.getOutputStream());
            outEndGame.println(s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        }catch (Exception e){e.printStackTrace();}
        return false;
    }
    @Override
    public boolean challenge(String word)
    {
        return C_and_Q("challenge",word);
    }
    public boolean query(String word)
    {
        return C_and_Q("query",word);
    }
    @Override
    public int passTurn(int turn)
    {
        currentTurn = (turn+1)%players.size();
        return currentTurn;

    }

    @Override
    public void tryPlaceWord(String word,int row,int coulmn,String direction) {
        String msg= "tryPlaceWord-"+id+"-"+word+"-"+row+"-"+coulmn+"-"+direction;
        myHostClientHandler.handelRequest(msg);

    }

    @Override
    public List<Tile> getHand() {
        return myTiles;
    }

    @Override
    public void drawTile() {
        this.bag.getRand();
    }
//add thread for the host player and logic to send to players

//    public String convertWordToString(Word word)
//    {
//        StringBuilder sb=new StringBuilder();
//        for (Tile tile:word.tileWord) {
//            sb.append(tile.getLetter());
//        }
//        return sb.toString();
//    }


}
