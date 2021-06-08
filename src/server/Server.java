package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class Server extends Thread {

    // Sockets
    private ServerSocket server;
    private Socket[] clients;

    // I/O
    private BufferedReader[] in;
    private PrintWriter[] out;

    private int clientCons; // Number of client connections

    // Constants
    private final int TIMEOUT = 60000; // 1 minute



    /**
     * Creates a server socket with a defined number of players and a port number
     * Also creates client and I/O arrays for server access and communication
     * @param playerNum number of players that will connect to the server
     * @param port port number to connect
     */
    public Server(int playerNum, int port) {

        try{
            server = new ServerSocket(port);    // Create a server socket with the port number
            server.setSoTimeout(TIMEOUT);       // Set a timeout for the server socket

            System.out.println("Server created.");
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        clients = new Socket[playerNum];       // Create client array

        in = new BufferedReader[playerNum];    // Create input stream array for clients
        out = new PrintWriter[playerNum];      // Create output stream array for clients

        clientCons = 0;

        new Thread(() -> {
            try {
                for (int i=0; i<playerNum; i++){
                    System.out.println("Waiting for clients.");
                    clients[i] = server.accept();       // Accept new clients and store them in the array

                    clientCons++;
                    System.out.println("New client connected on server side with address: " + clients[i].getInetAddress());

                    in[i] = new BufferedReader(new InputStreamReader(clients[i].getInputStream()));     // Input stream for current connected client
                    out[i] = new PrintWriter(clients[i].getOutputStream(), true);                       // Output stream for current connected client
                
                    sendMsg(String.valueOf(i), i);
                }
            }
            catch (SocketTimeoutException s) {
                System.err.println("Socket timed out!");
                System.exit(-1);
            } 
            catch (IOException e) {
                System.err.println(e.getMessage());
                System.exit(-1);
            }
        }).start();
    }

    /**
     * Closes the server
     */
    public void closeServer(){
        try {
            server.close();

            System.out.println("Server has closed");
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }


    /**
     * Closes the clients and the input and output streams
     */
    public void closeClients(){
        try {
            for (int i=0; i<clientCons; i++){
                clients[i].close();

                in[i].close();
                out[i].close();

                System.out.println("Clients and I/O have closed");
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }


    /**
     * Reads a string from the input stream of a specific client.
     * If input stream is empty, waits until input stream contains an input.
     * The input is read from a queue.
     * @param clientIdx index of a specific client
     * @return the string read or null if: client doesn't exist, client is closed or IOException occurs
     */
    public String readMsg(int clientIdx){
        if (clients[clientIdx] == null){
            System.err.println("Client doesn't exist");
            return null;
        }
        if (clients[clientIdx].isClosed()){
            System.err.println("Client is closed");
            return null;
        }
        if (in[clientIdx] == null){
            System.err.println("Input for client at [" + clientIdx + "] has been closed");
            return null;
        }
        
        try{
            String input = in[clientIdx].readLine();

            if (input == null){
                System.err.println("Client is closed");
                return null;
            }

            System.out.println("message read at server: " + input);

            return input;
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    /**
     * Sends a string to the output stream of a specific client which gets added to a queue
     * @param msg the string to be outputed
     * @param clientIdx index of a specific client
     */
    public void sendMsg(String msg, int clientIdx){
        if (clients[clientIdx] == null){
            System.err.println("Client doesn't exist");
            return;
        }
        if (clients[clientIdx].isClosed()){
            System.err.println("Client is closed");
            return;
        }
        if (out[clientIdx] == null){
            System.err.println("Output for client at [" + clientIdx + "] has been closed");
            return;
        }

        out[clientIdx].println(msg);
        System.out.println("message sent at server: " + msg);
    }

    
    /**
     * Returns the number of clients connected to the server
     * 
     * @return number of connections
     */
    public int getclientCons(){
        return clientCons;
    }

    /**
     * Returns the IP address of the server
     * 
     * @return IP address in a string format
     */
    public String getHost(){
        try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.err.println("Unknown host exception");
        }
        return "error";
    }
}