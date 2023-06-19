package view;

import Model.Data.Guest;

import java.util.Observable;
import java.util.Observer;

public class ViewSharedData implements Observer {
    ViewModel viewModel;
    Guest player;
    view.data.GameModelReceiver gameModelReceiver;
    String hostIp;
    String playerName;
    int hostPort;

    public ViewSharedData(ViewModel viewModel){
        this.viewModel = viewModel;
        gameModelReceiver = null;
        hostIp = "localhost";
    }

    public ViewModel getViewModel() {
        return viewModel;
    }

    public void setPlayer(Guest player) {
        this.player = player;
    }

    public Guest getPlayer() {
        return player;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setGameModelReceiver(view.data.GameModelReceiver gameModelReceiver) {
        this.gameModelReceiver = gameModelReceiver;
        this.gameModelReceiver.addObserver(this);
    }

    public view.data.GameModelReceiver getGameModelReceiver() {
        return gameModelReceiver;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.getViewModel().setModel(this.gameModelReceiver.getUpdatedModel());
    }
}