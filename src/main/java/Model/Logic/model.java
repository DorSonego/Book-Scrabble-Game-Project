package Model.Logic;

import javafx.beans.InvalidationListener;

import java.util.Observable;


public  class model extends Observable implements Ifacade{


    @Override
    public void serverConnect(int port, ClientHandler ch) {
            MyServer myServer=new MyServer(port, ch);
            myServer.runServer();
            setChanged();
            notifyObservers();

    }

    @Override
    public void localHost() {

    }

    @Override
    public void runGuestMode() {

    }
}

