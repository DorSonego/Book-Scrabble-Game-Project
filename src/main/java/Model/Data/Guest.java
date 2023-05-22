package Model.Data;

import Model.Logic.ClientHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;

public class Guest implements Observable {


    Host host;
    Socket hostSocket;
    List<Tile> myTiles;
    Board myBoard;
    Scanner in;
    Scanner out;
    guestClientHandler gs=new guestClientHandler();
    public Guest(int id)
    {

    }
    public void passTurn()
    {
        gs.passTurn();
    }
    public void connectToHost(int port,String ip)
    {
        try {
             hostSocket = new Socket(ip,port);
        }
        catch (IOException e)
        {
            throw new RuntimeException("unable to connect to Host");
        }
    }


}
