package Model.Data;

import Model.Logic.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class hostClientHandler implements ClientHandler {
    Scanner inputScanner;
    Host host;
    PrintWriter outputToGameServer;
    Map<String, Consumer<String>> functions =new HashMap<>();
    ForkJoinPool pool =new ForkJoinPool();
    Queue<Consumer> queue = new LinkedBlockingQueue<>();

    public hostClientHandler() {
        host=Host.getHost();
        functions.put("tryPlaceWord",(String input)->
        {
            String[] data=input.split("-");
            int ID_Current_Player = Integer.parseInt(data[1]);
            player p =host.players.get(ID_Current_Player);
            String wordInput=data[2];
            int row=Integer.parseInt(data[3]);
            int column=Integer.parseInt(data[4]);
            boolean isVert=data[5].equals("vertical");
            Tile[] wordTile =new Tile[wordInput.length()];
            for (int i = 0; i <wordInput.length(); i++) {
                for (Tile tile : p.getHand()) {
                    if (tile.letter==wordInput.charAt(i))
                    {
                        wordTile[i] =tile;
                    }
                }
            }
            Word word =new Word(wordTile,row,column,isVert);
            int k= host.tryPlaceWord(word);});
        functions.put("challenge",(String input)->
        {
            String[] strings = input.split("-");
            int playerID=Integer.parseInt(strings[0]);
            boolean challengeSuccessful=host.challenge(strings[2]);
        });
        functions.put("addPlayer",(String imput)->
        {
            host.addPlayer(imput.split("-")[0]);
        });
        functions.put("query",(String input)->
        {
            String[] strings = input.split("-");
            host.query(strings[1]);
        });
        functions.put("passTurn",(String input)->{
            host.passTurn(Integer.parseInt(input.split("-")[1]));
        });
        pool.submit(playersThreads());
    }


    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {


        String[] s = inFromClient.toString().split("-");
        queue.add(functions.get(s[0]));
        outToClient.write();


    }

    @Override
    public void close() {

    }
    public Runnable playersThreads()
    {
        //enter while game server is still running
        while (true)
        {

        }
    }
}
