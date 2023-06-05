package Model.Connection;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

    public class hostServer extends MyServer{
        ServerSocket server;
        Map<Integer,Socket> guests;
        int numOfGuests;
        ClientHandler clientHandler;
        int port;
        volatile boolean stop;

        public hostServer(int port, ClientHandler ch) {
            super(port, ch);
            this.server = null;
            this.guests = new HashMap<>();
            this.numOfGuests = 0;
            this.port = port;

        }

        @Override
        public void runServer() {
            try {
                server = new ServerSocket(port);
                server.setSoTimeout(1000);
                while (!stop) {
                    try {
                        Socket aClient = server.accept(); // blocking call

                        while(!stop&&this.numOfGuests<3)
                        {
                            if(!guests.containsValue(aClient))
                            {
                                guests.put(numOfGuests,aClient);
                                numOfGuests++;
                            }
                        }

                    } catch (Exception e) {}
                }
                server.close();
            } catch (Exception e) {
            }
        }

        public void start(){
            new Thread(()->runServer()).start();
        }

        public void close(){
            stop=true;
        }


    }


