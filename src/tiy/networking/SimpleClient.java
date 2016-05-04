package tiy.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Created by Justins PC on 4/27/2016.
 */
public class SimpleClient {
    public static String messageReceieved = "";
    //public static String userSocket = "";
    public static String userName = "";
    public static String userIp = "";
    public static String userString;
    public static double userServer;
    public static Socket clientSocket;
    public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

    public static void main(String [] args) throws Exception {

        clientSocket = new Socket("", 8005);
        userOptions();
    }

    public static void sendInfo() {

    }








    public static void chatBoc() throws Exception{

//        String serverResponse = in.readLine();
        System.out.println("Starting...... Chat");
        while (true) {
            System.out.println("Chatting with user: " + clientSocket.toString() );
            System.out.println("===================");
            System.out.println("Input text");
            System.out.println("===================");
            System.out.println("press (1) to exit chat");
            Scanner lineScanner = new Scanner(System.in);
            System.out.print(">");
            String userString = lineScanner.nextLine();
            ///Socket clientSocket = new Socket("localhost", 8005);
            //  PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            //   BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //  out.println(userString);

            if (userString.equals("1")) {
                userOptions();
            } else {
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);//sends input from client to server
                String sendInputTest = " :client sending input to server";
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //sends input into the server
                System.out.println("what was sent to server: " + in.toString() + sendInputTest); //prints out what we sent to the server
                System.out.println(userString);
                out.println(userString); //input from server
                String serverResponse;
                while ((serverResponse = in.readLine()) !=null) {
                    System.out.println(serverResponse);
                    if (serverResponse.isEmpty()) {
                        break;
                    }
                }
                }





        }
    }
    public static void userOptions() throws Exception {
        //Socket clientSocket = new Socket("localhost",8005);
       // PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
     //   BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

     //   out.println(userString);
        //clientSocket.close();
        System.out.println("==================================");
        System.out.println("Enter a option");
        System.out.println("==================================");
        System.out.println("Enter (?) to see a list of options");
        Scanner lineScanner = new Scanner(System.in);
        System.out.print(">");
        String userSelection = lineScanner.nextLine();

        while(true) {
            if(userSelection.equals("chat")) {
                chatBoc();
            }
            if(userSelection.equals("add")) {
                addUser();
            }
            if (userSelection.equals("exit")) {
                System.out.println("===(Exiting Client)===");
                System.exit(1);
                break;
            }
            if (userSelection.equals("?")) {
                optionList();
            }
        }


    }

    public static void addUser() {
        System.out.println("===================================================");
        System.out.println("input the user ip you would like to communicate with");
        System.out.println("===================================================");
        Scanner lineScanner = new Scanner(System.in);
        System.out.print(">");
       // String UsersIpname = lineScanner.nextLine();
       // System.out.println();

    }
    public static void optionList() throws Exception {
        System.out.println("0-Enter (chat) in order to send messages to the server");
        System.out.println("======================================================");
        System.out.println("0-Enter (exit) in order to close the client");
        System.out.println("======================================================");
        System.out.println("0-Enter (add) to add a user to your friends list");
        System.out.println("======================================================");
        userOptions();
    }


}
