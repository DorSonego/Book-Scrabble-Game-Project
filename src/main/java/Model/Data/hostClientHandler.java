package Model.Data;

import Model.Logic.ClientHandler;
import Model.Logic.DictionaryManager;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class hostClientHandler implements ClientHandler {
    Scanner inputScanner;
    Host host;
    PrintWriter outputToClient;
    Map<String, Function<String,String>> functions =new HashMap<>();
    ForkJoinPool pool =new ForkJoinPool();
    String answer;
    boolean queryAnswer;
    Boolean stop=false;
   // List<Function> assigments;

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
            return host.tryPlaceWord(word)+"";

        });

        functions.put("challenge",(String input)->
        {
            String [] strings = input.split("-");
            boolean ans=host.challenge(strings[1]);
            if(ans)
                return "true";
            return "false";
        });
        functions.put("addPlayer",(String input)->
        {
            return host.addPlayer(input.split("-")[0])+"";
        });
        functions.put("query",(String input)->
        {
            String [] strings = input.split("-");
            boolean ans=host.query(strings[1]);
            if(ans)
                return "true";
            return "false";
        });
        functions.put("passTurn",(String input)->{
           return ""+ host.passTurn(Integer.parseInt(input.split("-")[1]));
        });
        functions.put("endGame",(String input)->{
            host.endGame();
            return "game over";
        });
    }


    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
         inputScanner = new Scanner(inFromClient);
         outputToClient = new PrintWriter(outToClient);
         String s="";

        try {
            if(inputScanner.hasNextLine()){
                s = inputScanner.toString().split("-")[0];
            }
        } catch (Exception e){
            e.printStackTrace();}

        String ans =handelRequest(s);
        outputToClient.println(ans);
        outputToClient.flush();

//        DictionaryManager dictionaryManager=DictionaryManager.get();
//        String[] words2 = new String[queue.size()-1];
//        for(int i =0 , s=1; s<queue.size(); i++ , s++){
//            words2[i] = queue.get(s);


    }
//    public void runAssigments()
//    {
//        Executors.newSingleThreadExecutor().execute(new Runnable() {
//            @Override
//            public void run() {
//                playersThreads();
//            }
//        });
//    }
    public String getBoardState()
    {
        Tile[][] theBoard= host.myBoard.getTile();
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ;i<15;i++)
        {
            for(int j = 0;j<15;j++)
            {
                sb.append(theBoard[i][j].letter);
                sb.append("-");
            }
        }
        return sb.toString();
    }
    public String handelRequest(String input)
    {
        return functions.get(input).apply(input);
    }
    @Override
    public void close() {

    }
//    public Runnable playersThreads()
//    {
//       //enter while game server is still running
//        while (!stop)
//        {
//            //add blocking call if there isnt assigment
//
//        }
//    }
}
