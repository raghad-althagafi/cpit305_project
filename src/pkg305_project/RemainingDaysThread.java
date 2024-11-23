/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author shaha
 */
public class RemainingDaysThread extends Thread {

    private userInterface ui;
    private Event event;
    private static final ReentrantLock lock = new ReentrantLock();

    public RemainingDaysThread(userInterface ui, Event event) {
        super("RemainingDaysThread");
        this.ui = ui;
        this.event = event;
    }

    @Override
    public void run() {
        try {
            lock.lock(); //lock critical section
            String eventDate = event.getEventDate();
            int remaining = ui.remainingDays(eventDate); //calculate the remaining days for the event
            String time = null;
            if (remaining == 0) {
                time = "Event is today";
            } else if (remaining > 0) {
                time = "Event remaining days is : " + remaining;
            } else {
                time = "Event has passed";
            }
            JOptionPane.showMessageDialog(null, time);//show the remaining days in message dialog

        } finally {
            lock.unlock();
        }
    }
}
