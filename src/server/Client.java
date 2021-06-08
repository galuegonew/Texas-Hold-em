package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread{

    // Socket
    private Socket client;
    private int position;

    // I/O
    private BufferedReader in;
    private PrintWriter out;



    /**
     * Creates a client socket with a host name and a port number
     * @param host host name of the server
     * @param port port number of the server
     */
    public Client(String host, int port) {
        try {
            client = new Socket(host, port);
            
            System.out.println("Client created.");
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        new Thread(() -> {
            if (client.isConnected()){
                System.out.println("Client connected on client side.");
            }
            
            try{
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));    // Input stream for current connected client
                out = new PrintWriter(client.getOutputStream(), true);                      // Output stream for current connected client
                
                position = Integer.valueOf(readMsg());
            }
            catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }).start();
    }

    /**
     * 
     * @return position of the client
     */
    public int getPosition(){
        return position;
    }


    /**
     * Closes the client and the input and output stream
     */
    public void close(){
        try {
            client.close();

            in.close();
            out.close();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }


    /**
     * Reads a string from the server with the input stream of the client.
     * If input stream is empty, waits until input stream contains an input.
     * The input is read from a queue.
     * @return the number read or null if: client doesn't exist, client is not
     *          connected, server is closed or IOException occurs
     */
    public String readMsg(){
        if (client == null){
            System.err.println("Client doesn't exist");
            return null;
        }
        if (!client.isConnected()){
            System.err.println("Client is not connected");
            return null;
        }
        if (in == null){
            System.err.println("Input for client has been closed");
            return null;
        }

        try{
            String input = in.readLine();

            if (input == null){
                System.err.println("Server is closed");
                return null;
            }

            System.out.println("message read at client: " + input);

            return input;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    /**
     * Sends a string to the server which gets added to a queue
     * @param msg the number to be outputed
     */
    public void sendMsg(String msg){
        if (client == null){
            System.err.println("Server doesn't exist");
            return;
        }
        if (!client.isConnected()){
            System.err.println("Server is closed");
            return;
        }
        if (out == null){
            System.err.println("Output for client has been closed");
            return;
        }

        out.println(msg);
        System.out.println("message sent at client: " + msg);
    }
}