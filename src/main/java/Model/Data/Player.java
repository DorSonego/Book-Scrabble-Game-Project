package Model.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player  {

    public String name;
    List<Tile> givenTiles;
    int score;

    public Player(String name) {
        this.name = name;
        this.givenTiles = new ArrayList<>();
        this.score = 0;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public List<Tile> getAllGivenTiles() {
        return givenTiles;
    }

    public void setGivenTiles(List<Tile> givenTiles) {
        this.givenTiles = givenTiles;
    }





}
