package Model.Data;

import java.util.List;

public interface playerManagment {
    boolean hostPlayer();
    public void setName(String name);
    public void nextTurn(int turn);
    int getScore();
    public List<Tile> getHand();
    public Board getBoard();
}
