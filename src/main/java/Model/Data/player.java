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

    public void tryPlaceWord(Word word) {

    }

    public void challenge(Word word) {

    }

    public void passTurn() {

    }

    public Tile[] getHand() {
        return new Tile[0];
    }

    public void drawTile() {

    }
}
