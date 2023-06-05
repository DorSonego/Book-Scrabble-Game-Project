package Model.Connection;

import Model.Data.Tile;

import java.util.ArrayList;
import java.util.List;

public interface IPlayer {
    int score=0;
    List<Tile> tiles=new ArrayList<>();
    public void tryPlaceWord(String word,int row,int coulmn,String direction);
    public boolean challenge(String word);
    public int passTurn(int turn);
    public List<Tile> getHand();
    public void drawTile();


}
