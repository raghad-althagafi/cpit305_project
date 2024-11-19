/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author shaha
 */
public class RemainingDaysThread extends Thread {

    private userInterface ui;
    private Event event;
    private Details detail;

    public RemainingDaysThread(userInterface ui, Event event, Details detail) {
        super("RemainingDaysThread");
        this.ui = ui;
        this.event = event;
        this.detail = detail;
    }

    @Override
    public void run() {
        try {
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
            //to ensure that the panel has been created and prevent errors
            int attempts = 0;
            while (detail.getPanel() == null && attempts < 10) {
                Thread.sleep(50); // to ensure that the remaining days will appear in the details page
                attempts++;
            }

            JPanel panel = detail.getPanel();
            if (panel == null) {
                throw new IllegalStateException("Panel is null in Details object!");//if it still null then an exception will be thrown
            }

            detail.addRemainingDays(time);//adding the remaing days in the details page

            PrintWriter out = new PrintWriter(new FileWriter(".\\Event.txt")); //write event remaing days in a file
            out.println(time);
            out.close();//close 
        } catch (IOException e) {
            e.getMessage();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
