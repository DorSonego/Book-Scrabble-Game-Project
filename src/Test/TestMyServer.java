package Test;

import Files.Model.Logic.ClientHandler;
import Files.Model.Logic.MyServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class TestMyServer {
    public static class ClientHandler1 implements ClientHandler {
        PrintWriter out;
        Scanner in;
        @Override
        public void handleClient(InputStream inFromclient, OutputStream outToClient) {
            out=new PrintWriter(outToClient);
            in=new Scanner(inFromclient);
            String text = in.next();
            out.println(new StringBuilder(text).reverse().toString());
            out.flush();
        }

        @Override
        public void close() {
            in.close();
            out.close();
        }

    }
    public static void client1(int port) throws Exception{
        Socket server=new Socket("localhost", port);
        Random r=new Random();
        String text = ""+(1000+r.nextInt(100000));
        String rev=new StringBuilder(text).reverse().toString();
        PrintWriter outToServer=new PrintWriter(server.getOutputStream());
        Scanner in=new Scanner(server.getInputStream());
        outToServer.println(text);
        outToServer.flush();
        String response=in.next();
        if(response==null || !response.equals(rev))
            System.out.println("problem getting the right response from your server, cannot continue the test (-100)");
        in.close();
        outToServer.println(text);
        outToServer.close();
        server.close();
    }

    public static boolean testServer() {
        boolean ok=true;
        Random r=new Random();
        int port=6000+r.nextInt(1000);
        MyServer s=new MyServer(port, new ClientHandler1());
        int c = Thread.activeCount();
        s.start(); // runs in the background
        try {
            client1(port);
        }catch(Exception e) {
            System.out.println("some exception was thrown while testing your server, cannot continue the test (-100)");
            ok=false;
        }
        s.close();

        try {Thread.sleep(2000);} catch (InterruptedException e) {}

        if (Thread.activeCount()!=c) {
            System.out.println("you have a thread open after calling close method (-100)");
            ok=false;
        }
        return ok;
    }
}
