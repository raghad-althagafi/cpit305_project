/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author msbbr
 */
public class PrintThread extends Thread{
    
    private Event event;

    public PrintThread(Event event) {
        super("PrintThread");
        this.event = event;
    }
    
    public void run(){
        try{
                PrintWriter out = new PrintWriter(new FileWriter(".\\Event.txt")); //write event information in a file
                out.println("Event Name: " + event.getEventName());// write event details in file
                out.println("Event Date: " + event.getEventDate());
                out.println("Event Faculty: " + event.getFaculty());
                out.println("Event Location: " + event.getLocation());
                out.println("Event Time: " + event.getTime());
                out.println("Event Details: " + event.getDetails());
                out.close();//close 
        }
        catch(IOException e){
            e.getMessage();
        }
    }
    
}
