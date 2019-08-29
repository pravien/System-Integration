/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 *
 * @author pravien
 */
public class Server {

    ServerSocket serverSocket;
    static HashMap<String, Double> user = new HashMap();

    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(59090)) {
            System.out.println("The date server is running...");
            while (true) {
                try (Socket socket = listener.accept()) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String[] req = in.readLine().split(" ");
                    String bankacc = req[0];
                    Double amount = Double.parseDouble(req[1]);
                    if(handleReq(bankacc,amount)){
                        out.print("Balance: "+user.get(bankacc));
                    }
                    else{
                        out.print("Lovro says NO!");
                    }
                    out.flush();
                    socket.close();
                }
            }
        }
    }

    public static Boolean handleReq(String bankacc, Double amount) {
        if (!user.containsKey(bankacc)) {
            user.put(bankacc, 0.0);
        }
        Double currentBalance = user.get(bankacc);
        Double newBalance = currentBalance + amount;
        if (newBalance >= 0.0) {
            user.put(bankacc, newBalance);
            return true;
        } else {
            return false;
        }

    }

}
