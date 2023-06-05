package Model.Connection;

import Model.Data.Board;
import Model.Data.Player;
import Model.Data.Tile;

import java.util.HashMap;
import java.util.Map;


public class Game {
    Board board;
    Tile.Bag bag;
    Map<String, Player> players;
    Player player;
    int turn;
    int numOfPlayers;

    public Game() {
        this.board = Board.getBoard();
        this.bag = Tile.Bag.getBag();
        this.players = new HashMap<>();
        this.turn = 0;
        this.numOfPlayers = 0;
    }

    public void addPlayer(String name) {
        if(numOfPlayers<3) {
            if(!players.containsKey(name)) {
                players.put(name, new Player(name));
                numOfPlayers++;
            }
        }
    }

    public void removePlayer(String name) {
        if(numOfPlayers>0) {
            players.remove(name);
            numOfPlayers--;
        }
    }

    public Player getPlayer(String name) {
        return players.get(name);
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public Board getBoard() {
        return board;
    }

    public Tile.Bag getBag() {
        return bag;
    }

    public Map<String,Player> getPlayers() {
        return players;
    }

}