package Model.Data;

import Model.Logic.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class gameServerHandler implements ClientHandler {


    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {

    }

    @Override
    public void close() {

    }
}
