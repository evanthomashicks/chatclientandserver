package tiy.networking;

import jodd.json.meta.JSON;
import org.omg.CORBA.TIMEOUT;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Justins PC on 4/27/2016.
 */
public class Message {

    public String text;

    public String name;

    public String time;
   // private LocalDateTime time;

//    protected  LocalDateTime getTimeCreated() {
//        if (time == null) {
//            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
//            if (timeCreatedString == null)  {
//                time = LocalDateTime.parse(timeCreatedString);
//            }
//        }
//        return  time;
//    }
//    protected void setTimeCreated(LocalDateTime timeCreated) {
//        this.time =  timeCreated;
//        this.timeCreatedString = timeCreated.toString();
//        System.out.println("Time created string: " + timeCreatedString);
//    }



    public Message(String time, String user, String text) {
        this.name = user;
        this.text = text;
        this.time = time;
    }

    public Message() {

    }


    @Override
    public String toString() {
        String finalString = ("received on " +  time + " from:" + name + " =>" + text);
        return  finalString;

    }



}
