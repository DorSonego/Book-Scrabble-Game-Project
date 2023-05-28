package Model.Data;

import java.util.ArrayList;

public class player  {

    public int id;
    public  String name;
    ArrayList<Tile> givenTiles;
    int points;

    public player(String name, int id) {
        this.name = name;
        this.id = id;
        this.givenTiles = new ArrayList<>();
        this.points = 0;
    }

//    @Override
//    public void tryPlaceWord(Word word) {
//
//    }
//
//    @Override
//    public void challenge(Word word) {
//
//    }
//
//    @Override
//    public void passTurn() {
//
//    }
//
//    @Override
//    public Tile[] getHand() {
//        return new Tile[0];
//    }
//
//    @Override
//    public void drawTile() {
//
//    }
}
