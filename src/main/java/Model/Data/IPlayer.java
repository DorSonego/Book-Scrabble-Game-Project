package Model.Data;

public interface IPlayer {
    int score=0;
    public void tryPlaceWord(Word word);
    public void challenge(Word word);
    public void passTurn();
    public Tile drawTile();

}
