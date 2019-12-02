package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalculatePrice implements Runnable {

    private int applePrice = 20;
    private int bananaPrice = 30;
    private int orangePrice = 10;
    private Socket socket = null;

    public CalculatePrice(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputData = new DataInputStream(socket.getInputStream());

            // send price of fruit
            outputData.writeInt(applePrice);            
            outputData.writeInt(bananaPrice);
            outputData.writeInt(orangePrice);

            while (true) {
                // receive the quantities of fruit
                int apple = inputData.readInt();
                int banana = inputData.readInt();
                int orange = inputData.readInt();

                // calculate the total price and send it to client
                int totalPrice = (apple * applePrice) + (orange * orangePrice) + (banana * bananaPrice);
                outputData.writeInt(totalPrice);
            }
            
        } catch (IOException ex) {
            ex.toString();
        }
    }

}
