package Model.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class player  {

    public int id;
    public  String name;
    List<Tile> givenTiles;
    Tile.Bag bag;
    int points;

    public player(String name, int id) {
        this.name = name;
        this.id = id;
        this.givenTiles = new ArrayList<>();
        this.points = 0;
        bag = Tile.Bag.getBag();

    }

    public int tryPlaceWord(Word word) {
        return 0;
    }

    public void challenge(Word word) {


    }

    public void passTurn() {

    }

    public List<Tile> getHand() {
       return givenTiles;
    }

    public void drawTile() {

    }

    public void removeTile(Tile tile) {
        Arrays.stream(bag.t).toList().remove(tile);
    }

    public void addScore(int result) {
        this.points+=result;
    }




}
