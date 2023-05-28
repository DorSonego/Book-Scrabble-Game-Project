package Model.Data;

public class player implements IPlayer {
    public int id;
    public  String name;

    public player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public void tryPlaceWord(Word word) {

    }

    @Override
    public void challenge(Word word) {

    }

    @Override
    public void passTurn() {

    }

    @Override
    public Tile[] getHand() {
        return new Tile[0];
    }

    @Override
    public void drawTile() {

    }
}
