package tiy.networking;

import javafx.beans.property.SimpleMapProperty;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;





/**
 * Created by Justins PC on 4/27/2016.
 */
public class ServerThread implements Runnable {
   // public String inputLine;
    Timer timer = new Timer();
    public LocalDateTime timeStart;
    public Socket clientSocket;
    public SimpleServer chatServer;
//    public ChatHistoryList chatHistory = new ChatHistoryList();

//    public ArrayList<Socket> clientsOnServer = new ArrayList<>();
    // public static ArrayList<Message> messageArrayList = new ArrayList<Message>();

    public ServerThread(Socket incomingSocket, SimpleServer chatServerInput) {
        clientSocket = incomingSocket;
        chatServer = chatServerInput;
    }

    public void run() {
        System.out.println("Running server.....");
        try {
            System.out.println("Incoming connection from " + clientSocket.getInetAddress().getHostAddress());
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outPutToClient = new PrintWriter(clientSocket.getOutputStream(), true);
//            clientsOnServer.add(clientSocket);
//            System.out.println("Number of Clients on server: " + clientsOnServer.size());
            String inputLine;

// String clientName = clientSocket.toString();
//                    BufferedReader clientChatInput = inputFromClient;
            while ((inputLine = inputFromClient.readLine()) != null) {
                String userName = clientSocket.toString();
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("E M/d @ h:ma");
                String time = now.format(myFormatter).toString();
                String text = inputLine;
                Message lastMessage = new Message(time,text,userName);
                chatServer.addMessage(lastMessage);
                System.out.println(chatServer.toString()); // will now get you the first message sent to the server
                outPutToClient.println(chatServer.toString());
            }
        } catch (Exception excpetion) {

            try {
                chatServer.removeThread();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Client closed client connection");
            }
    }




    /*public  void saveInputFromClient() throws Exception{
        System.out.println("==========================");
        System.out.println(" Saving Input form client ");
        System.out.println("==========================");
        File clientInput = new File(clientName + ".txt");
        FileWriter clientInputWriter = new FileWriter(clientInput);
        clientInputWriter.write("Name of Client: " + clientName);
      //  clientInputWriter.write("Client input: " + clientInputArrayList  );
        clientInputWriter.close();
    }*/

    public  void makeUsername() throws Exception{
        // this is how we read from the client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // this is how we write back to the client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
        // read from the input until the client disconnects
        String clientName = clientSocket.toString();

    }
//    public void startTime() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("This is the timer ");
//            }
//        }, 1000);
//
//    }



}

