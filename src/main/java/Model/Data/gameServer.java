package Model.Data;

import Model.Logic.DictionaryManager;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class gameServer {
    Socket server;
    DictionaryManager dictionaryManager = DictionaryManager.get();


 public gameServer(int port, String address) throws IOException {
        if (address == null){
            address = "localhost";
        }
        server = new Socket(address, port);
    }

    public boolean challenge(String[] dictionaries, String query) {
        try {
            String dics = String.join(",", dictionaries);
            String request = "c," + dics + "," + query;

            PrintWriter out = new PrintWriter(server.getOutputStream());
            out.println(request);
            out.flush();

            Scanner in = new Scanner(server.getInputStream());
            String response = in.next();

            return Boolean.parseBoolean(response);
        } catch (IOException e) {
            System.out.println("Your code ran into an exception");
        }

        return false;
    }

    public Boolean query(String[] dictionaries, String query) throws IOException {
        String dic = String.join(",", dictionaries);

        try (PrintWriter out = new PrintWriter(server.getOutputStream());
             Scanner in = new Scanner(server.getInputStream())) {

            out.println("q," + dic + "," + query);
            out.flush();
            String res = in.next();
            return Boolean.valueOf(res);
        }
    }

}
