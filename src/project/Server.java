package project;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {

    public static void main(String[] args) {
        try {
            System.out.println("Server Started ......");
            ServerSocket socket = new ServerSocket(1050);

            while (true) {
                Thread t = new Thread(new CalculatePrice(socket.accept()));
                t.start();
            }

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

}
