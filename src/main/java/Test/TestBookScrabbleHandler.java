package Test;

import Model.Logic.BookScrabbleHandler;
import Model.Logic.MyServer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


public class TestBookScrabbleHandler {
    public static String[] writeFile(String name) {
        Random r=new Random();
        String txt[]=new String[10];
        for(int i=0;i<txt.length;i++)
            txt[i]=""+(10000+r.nextInt(10000));

        try {
            PrintWriter out=new PrintWriter(new FileWriter(name));
            for(String s : txt) {
                out.print(s+" ");
            }
            out.println();
            out.close();
        }catch(Exception e) {}

        return txt;
    }
    public static void runClient(int port,String query,boolean result) {
        try {
            Socket server=new Socket("localhost",port);
            PrintWriter out=new PrintWriter(server.getOutputStream());
            Scanner in=new Scanner(server.getInputStream());
            out.println(query);
            out.flush();
            String res=in.next();
            if((result && !res.equals("true")) || (!result && !res.equals("false")))
                System.out.println("problem getting the right answer from the server (-10)");
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            System.out.println("your code ran into an IOException (-10)");
        }
    }
    public static void testBSCH() {
        String s1[]=writeFile("s1.txt");
        String s2[]=writeFile("s2.txt");

        Random r=new Random();
        int port=6000+r.nextInt(1000);
        MyServer s=new MyServer(port, new BookScrabbleHandler());
        s.start();
        runClient(port, "Q,s1.txt,s2.txt,"+s1[1], true);
        runClient(port, "Q,s1.txt,s2.txt,"+s2[4], true);
        runClient(port, "Q,s1.txt,s2.txt,2"+s1[1], false);
        runClient(port, "Q,s1.txt,s2.txt,3"+s2[4], false);
        runClient(port, "C,s1.txt,s2.txt,"+s1[9], true);
        runClient(port, "C,s1.txt,s2.txt,#"+s2[1], false);
        s.close();
    }
}
