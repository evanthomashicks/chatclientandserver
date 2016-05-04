package tiy.networking;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by Justins PC on 4/27/2016.
 */
public class SimpleServer {
    // public static Message saveMessage;
    // public static ArrayList<Message> messageArrayList = new ArrayList<Message>();
    public ArrayList<Thread> clientsOnServer = new ArrayList<>();
    public ChatHistoryList chatHistory = new ChatHistoryList();
    //public static HashMap<String,ArrayList<Message>>();
    Timer timer = new Timer();
    //public static String userHashOut;
    ///public static String clientName;
    ///public static String inputLine;;
    //public static ArrayList<String> clientInputArrayList = new ArrayList<String>();



    public static void main (String [] args) throws Exception {
            new SimpleServer().startServer();

    }
        public void startServer()throws Exception {
            //retriveChatHistory();
            ServerSocket serverListener = new ServerSocket(8005);
            while (true) {
                retriveChatHistory();
                Socket clientSocket = serverListener.accept();
                ServerThread localThread = new ServerThread(clientSocket, this);
                Thread newThread = new Thread(localThread);
                clientsOnServer.add(newThread);
                System.out.println("Number of current threads: " + clientsOnServer.size());
                //startTime();
                newThread.start();
            }
        }

//        public void addThread() {
//            clientsOnServer.add(newThread);
//            System.out.println("Number of current threads: " + clientsOnServer.size());
//        }
//
        public  void removeThread() throws Exception {
            System.out.println("=====removing thread client left=====");
            clientsOnServer.remove(Thread.currentThread());
            System.out.println("Number of current threads: " + clientsOnServer.size());
            if (clientsOnServer.size() == 0) {
                exitAndSave();
            }
        }

        public void addMessage(Message lastMessage) {
            System.out.println("length before new message: " + chatHistory.messageArrayList.size());
            chatHistory.messageArrayList.add(lastMessage);
            System.out.println("Length after adding new message: " + chatHistory.messageArrayList.size());
        }

        public void exitAndSave() throws Exception {
            savechatHistory();

        }
//        public void returnChatHistory() throws FileNotFoundException {
//            Scanner fileScanner = new Scanner(new File("chatHistory.json"));
//            fileScanner.useDelimiter("\\Z");
//            String fileContents = fileScanner.next();
//            JsonParser ChatHistory = new JsonParser();
//            Message returnChatHistory = ChatHistory.parse(fileContents, Message.class);
//            System.out.println(fileContents + " " + Message.class);
//            System.out.println("Stuff:" + fileContents + Message.class);
//            ArrayList<Message> tempoaryArray = returnChatHistory
//            for (int count = 0; count < messageArrayList.size(); count++){
//                messageArrayList.add(tempoaryArray.get(count));
//            }
//            System.out.println("*******************************************************");
//            System.out.println("             Restored previous List                    ");
//            System.out.println("*******************************************************");
//
//
//
//        }
//
//        public static Message readChatHistory() throws IOException {
//
//        }

    public void savechatHistory() throws IOException {
            System.out.println("=======================================================================");
            System.out.println("Number of messages in the array: " + chatHistory.messageArrayList.size());
            System.out.println("=======================================================================");
            System.out.println("===========(Saving Chat History)===========");
            File chatHistoryList = new File("chatHistory.json");
            JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
            System.out.println(chatHistory);
            String jsonString = jsonSerializer.serialize(chatHistory);
            System.out.println("<====================(ChatHistory)====================>");
            System.out.println(jsonString);
            FileWriter jsonWriter = new FileWriter(chatHistoryList);
             try {
                jsonWriter.write(jsonString);
                System.out.println("===========(Done Saving)===========");
                jsonWriter.close();
                 System.out.println("==================(Exiting Server)==================");
                 System.exit(1);
                // stuff
            } catch (NullPointerException  | IOException ex) {
                ex.printStackTrace();
                // Handle both exceptions
            }

        }
//
//
//
    public ChatHistoryList retriveChatHistory() throws  IOException {
        try {
            Scanner fileScanner = new Scanner(new File("chatHistory.json"));
            fileScanner.useDelimiter("\\Z");
            String fileContents = fileScanner.next();
            JsonParser ChatParser = new JsonParser();
            chatHistory = ChatParser.parse(fileContents, ChatHistoryList.class);
//            System.out.println(fileContents + " " + Message.class);
//            System.out.println("Stuff:" + fileContents + Message.class);
//            ArrayList<Message> theList =  returnChatList.messageArrayList;
//            for (int count = 0; count < theList.size(); count++){
//               chatHistory.messageArrayList.add(theList.get(count));
//            }
            System.out.println("*******************************************************");
            System.out.println("             Restored previous Chat                ");
            System.out.println("*******************************************************");
            return chatHistory;
        } catch (IOException e) {
            System.out.println("chat restore failed");

            return null;
        }
    }


        // start a server on a specific port
        /*
        ServerSocket serverListener = new ServerSocket(8005);
        while (true) {
            try {
                Socket clientSocket = serverListener.accept();
                // display information about who just connected to our server
                System.out.println("Incoming connection from " + clientSocket.getInetAddress().getHostAddress());
                // this is how we read from the client
                BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // this is how we write back to the client
                PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                // read from the input until the client disconnects
                String clientName = clientSocket.toString();
                ////
                while ((inputLine = inputFromClient.readLine()) != null) {
                    System.out.println("Received message: " + inputLine + " from " + clientName);
                    outputToClient.println("Message received loud and clear");
                    System.out.println("Adding client text to array");
                    clientInputArrayList.add(inputLine);
                    System.out.println("Client text added to array list " + inputLine);
                }
            } catch (Exception excpetion) {
                System.out.println("Client closed client connection");
            }
            System.out.println("Done with simple server");
        }

    }*/

    @Override
    public String toString() {
        String history = "Chat History:  \r\n";
        String lineBreakers = "\r\n";
        for (int count= 0; count < chatHistory.messageArrayList.size(); count++) {
            history += chatHistory.messageArrayList.get(count) + lineBreakers;
        }

        history += lineBreakers;

        return  history;
    }




    public static void makeUserName() {
        System.out.println("Please enter a user name for the server");
        Scanner lineScanner = new Scanner(System.in);
        System.out.println("=======================================");
        System.out.print(">");
        //clientName = lineScanner.nextLine();
        System.out.println("=======================================");

    }
//    public static void saveUserChat() {
//        HashMap<String,<Message>> saveChatHash;
//    public void startTime() {
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("==================This is the timer================= ");
//            }
//        }, 6000);
//
//
//    }
}
