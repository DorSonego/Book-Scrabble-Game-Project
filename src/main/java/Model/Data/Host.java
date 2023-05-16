package Model.Data;

import Model.Logic.Dictionary;
import Model.Logic.DictionaryManager;
import Model.Logic.MyServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Host extends Observable implements IPlayer {
    Board myBoard;
    int id=0;
    Tile.Bag bag;
    ScoreBoard scoreBoard;
    MyServer myServer=new MyServer();
    public Host()
    {
        this.myBoard=Board.getBoard();
        this.bag=Tile.Bag.getBag();
        this.scoreBoard=new ScoreBoard();

    }

    public Board sendBoard()
    {
        return myBoard;
    }
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }


    public String convertWordToString(Word word)
    {
        StringBuilder sb=new StringBuilder();
        for (Tile tile:word.tileWord) {
            sb.append(tile.getLetter());
        }
        return sb.toString();
    }

    @Override
    public void tryPlaceWord(Word word) {
       int score= myBoard.tryPlaceWord(word);
       if (score!=0)
       {
           scoreBoard.addScore(id,score);
           setChanged();
           notifyAll();
       }

    }

    @Override
    public void challenge(Word word) {
    Boolean answer = DictionaryManager.get().challenge(convertWordToString(word));

    }

    @Override
    public void passTurn() {

    }

    @Override
    public Tile drawTile() {
     return bag.getRand();
    }

}