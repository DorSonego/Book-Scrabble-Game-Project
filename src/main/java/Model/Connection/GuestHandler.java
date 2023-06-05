package Model.Connection;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class GuestHandler implements ClientHandler {

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        Boolean flag=true;
        Host getHost = Host.getHost();
        Scanner inFromClient = new Scanner(inFromclient);
        PrintWriter outputToClient = new PrintWriter(outToClient);
        String command = "";
        while (flag) {
            try {
                if (inFromClient.hasNextLine()) {
                    command = inFromClient.toString().split("-")[0];
                    switch (command) {
                        case "challenge" -> {
                            boolean result = getHost.challenge(inFromClient.toString().split("-")[1]);
                            if(result) {
                                outputToClient.println("true");
                                outputToClient.flush();
                            }else{
                                    outputToClient.println("false");
                                    outputToClient.flush();
                                }
                            flag=false;
                            }
                            case "query" ->{
                                boolean result = getHost.query(inFromClient.toString().split("-")[1]);
                                if(result) {
                                    outputToClient.println("true");
                                    outputToClient.flush();
                                }else{
                                    outputToClient.println("false");
                                    outputToClient.flush();
                                }
                                flag=false;
                            }
                            case "tryPlaceWord" ->{
                            String [] instractions=inFromClient.toString().split("-");
                                int result = getHost.tryPlaceWord(instractions[1]
                                        , Integer.parseInt(instractions[2])
                                        ,Integer.parseInt(instractions[3])
                                        , instractions[4]);

                                outputToClient.println("word accepted,your score is " + result);
                                outputToClient.flush();
                                flag=false;
                            }
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
            inFromClient.close();
            outputToClient.close();


        }
    }
    @Override
    public void close() {
    }
}
