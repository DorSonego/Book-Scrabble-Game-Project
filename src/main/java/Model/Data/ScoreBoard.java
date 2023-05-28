package Model.Data;

import java.util.*;

public class ScoreBoard {
    Map <String,Integer> scores = new HashMap<>();
    //needs an implementation
    public ScoreBoard(List<player> players)
    {
        for (Model.Data.player player : players) {
            scores.put(player.name, player.points);
        }

    }
    public void addScore(String namePlayer,int score){
        this.scores.put(namePlayer,this.scores.get(namePlayer)+score);

    }

    public int getScore(String namePlayer){
        return this.scores.get(namePlayer);
    }
}
