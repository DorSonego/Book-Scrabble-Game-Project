package Model.Connection;

import Model.Data.Board;
import Model.Data.Tile;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class Host {

    hostServer hostServer;
    Game game;
    public static Host host;
    String ip;
    int port;

    public Host(int port)
    {
        hostServer = new hostServer(port, new GuestHandler());
        Game game = new Game();
        hostServer.start();
        ip="localhost";
        this.port=port;
    }
    public static Host getHost()
    {
        if (host==null)
            host=new Host(3000);
        return host;
    }
    private String sendToServer(String word){
        String result = "";
        try {
            Socket socket = new Socket(ip, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            Scanner in = new Scanner(socket.getInputStream());
            out.println(word);
            out.flush();
            result = in.nextLine();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void addPlayer(String playerName) {
        if(game.getPlayers().size()<4){
            game.addPlayer(playerName);
        }
    }
    public String challenge(String word){
        return sendToServer(word);
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


    public boolean query(String word)
    {
        return C_and_Q("query",word);
    }


    public int passTurn(int turn)
    {
        currentTurn = (turn+1)%players.size();
        return currentTurn;

    }


    public int tryPlaceWord(String word,int row,int coulmn,String direction) {
        String msg= "tryPlaceWord"+"-"+word+"-"+row+"-"+coulmn+"-"+direction;
        myHostClientHandler.handelRequest(msg);

    }


    public List<Tile> getHand() {
        return myTiles;
    }


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
