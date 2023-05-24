package Model.Data;

import Model.Logic.ClientHandler;
import Model.Logic.DictionaryManager;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class hostClientHandler implements ClientHandler {
    Scanner inputScanner;
    Host host;
    PrintWriter outputToGameServer;
    Map<String, Consumer<String>> functions =new HashMap<>();
    ForkJoinPool pool =new ForkJoinPool();
    String answer;
    boolean queryAnswer;

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
            answer= host.tryPlaceWord(word)+"";});
        functions.put("challenge",(String input)->
        {
            String [] strings = input.split("-");


        });
        functions.put("addPlayer",(String input)->
        {
            host.addPlayer(input.split("-")[0]);
        });
        functions.put("query",(String input)->
        {
            String[] strings = input.split("-");
        });
        functions.put("passTurn",(String input)->{
            host.passTurn(Integer.parseInt(input.split("-")[1]));
        });
        functions.put("endGame",(String input)->{
            host.endGame();
        });
        pool.submit(playersThreads());
    }


    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
         inputScanner = new Scanner(inFromClient);
         outputToGameServer = new PrintWriter(outToClient);
         String s="";

        try {
            if(inputScanner.hasNextLine()){
                s = inFromClient.toString().split("-")[0];
            }
        } catch (Exception e){
            e.printStackTrace();}

        String ans =handelRequest(s);

//        DictionaryManager dictionaryManager=DictionaryManager.get();
//        String[] words2 = new String[queue.size()-1];
//        for(int i =0 , s=1; s<queue.size(); i++ , s++){
//            words2[i] = queue.get(s);


    }
    public String handelRequest(String input)
    {
        return "";
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
