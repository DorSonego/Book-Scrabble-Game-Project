package Model.Data;

import Model.Logic.ClientHandler;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Scanner;

public class Guest implements Observable {


    Host host;
    Socket hostSocket;
    List<Tile> myTiles;
    Board myBoard;
    InputStream in;
    OutputStream out;
    hostClientHandler hch=new hostClientHandler();
    public Guest(int id)
    {

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
    public void sendRequest(String s)
    {

        //get string as query
        hch.handleClient(in,out);
    }


    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
