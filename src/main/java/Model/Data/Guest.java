package Model.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Guest implements Observer, IPlayer {

    Host host;
    List<Tile> myTiles;
    Board myBoard;
    public Guest(int id)
    {
        this.host=new Host();
        this.myTiles= new ArrayList<>();
        this.myBoard=host.myBoard;
        host.addObserver(this);
        myTiles.add(drawTile());
    }

    @Override
    public void tryPlaceWord(Word word) {
        host.tryPlaceWord(word);
    }

    @Override
    public void challenge(Word word) {
        host.challenge(word);
    }

    @Override
    public void passTurn() {

    }

    @Override
    public Tile drawTile() {
        return host.drawTile();
    }

    @Override
    public void update(Observable o, Object arg) {
        myBoard=host.myBoard;
    }
}
