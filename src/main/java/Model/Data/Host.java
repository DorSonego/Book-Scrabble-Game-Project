package Model.Data;

import Model.Logic.*;
import Model.Logic.Dictionary;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.*;

public class Host extends Observable  {
    Board myBoard;
    static Host host;
    int id=0;int currentTurn=0;
    List<player> players=new ArrayList<>();
    Tile.Bag bag;
    ScoreBoard scoreBoard;
    //add socket to game server
    Socket socket = new Socket();
    MyServer myServer=new MyServer(socket.getPort(),new hostClientHandler());
    gameServerHandler serverHandler=new gameServerHandler();

    public Host()
    {
        this.myBoard=Board.getBoard();
        this.bag=Tile.Bag.getBag();
        this.scoreBoard=new ScoreBoard();
    }
    public void addPlayer(String name)
    {
        players.add(new player(name,++id));
    }
    public static Host getHost()
    {
        if (host==null)
            host=new Host();
        return host;
    }
    public int tryPlaceWord(Word word)
    {
        int result= myBoard.tryPlaceWord(word);
        if (result > 0);
        //add a-lot

    }
    public boolean challenge(String word)
    {
        BookScrabbleHandler bookHandler=new BookScrabbleHandler();
        InputStream in=
                bookHandler.handleClient();
    }
    public boolean query(String word)
    {
        return DictionaryManager.get().query(word);
    }

    public void passTurn(int turn)
    {
        currentTurn=turn=(turn+1)%players.size();
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
