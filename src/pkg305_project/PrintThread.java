/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msbbr
 */
public class PrintThread extends Thread {

    private Event event;
    private static final ReentrantLock lock = new ReentrantLock();

    public PrintThread(Event event) {
        super("PrintThread");
        this.event = event;
    }

    @Override
    public void run() {
        try {
            lock.lock(); //lock critical section
            PrintWriter out = new PrintWriter(new FileWriter(".\\Event.txt")); //write event information in a file
            out.println("Event Name: " + event.getEventName());// write event details in file
            out.println("Event Date: " + event.getEventDate());
            out.println("Event Faculty: " + event.getFaculty());
            out.println("Event Location: " + event.getLocation());
            out.println("Event Time: " + event.getTime());
            out.println("Event Details: " + event.getDetails());
            out.close();//close 

            File file = new File(".\\Event.txt"); //create file object
            if (file.exists()) {
                Desktop.getDesktop().open(file); //open file in user's window
            } else {
                System.out.println("File does not exist!");
            }
        } catch (IOException e) {
            e.getMessage();

        } finally {
            lock.unlock();
        }
    }

}
