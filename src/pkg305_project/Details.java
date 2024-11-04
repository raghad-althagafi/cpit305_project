/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Details {
    private Event event;
    // Constructor for the Details class
    Details(Event event) {
        this.event = event;
    }
    public void DetailPage() {
        // Create a JFrame for the details window
        JFrame details = new JFrame("Event Details");
        details.setSize(800, 600); // Set size of the frame
        details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        details.setBackground(ColorsFonts.lightPurple); // Set background color (not effective on JFrame)
        details.setLocationRelativeTo(null);

        // Create a JPanel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for manual positioning
        panel.setBounds(0, 0, 1000, 900); // Set position and size
        panel.setBackground(ColorsFonts.lightPurple); // Set panel background color
        
        // Create a label for the event name
        JLabel events = new JLabel();
        events.setText("Event Name");
        events.setVerticalAlignment(JLabel.TOP); // Align label to the top
        events.setHorizontalAlignment(JLabel.CENTER); // Center align text
        events.setFont(ColorsFonts.fontButton); // Set font style and size
        events.setForeground(ColorsFonts.darkPurple); // Set text color
        events.setBounds(80, 100, 200, 100); // Set position and size of the label

        
         //open in file button
//        JButton openFile = createStyledButton("Open in File");
//        openFile.setBounds(250, 450, 200, 50); // Set position and size of the button
        
        // Add action listener to the 'open in file' button
//        openFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try{
//                PrintWriter out = new PrintWriter(new FileWriter(".\\Event.txt")); //write event information in a file
//                out.println("Event Name: ");// write detaila in file
//                out.println("Event Date: ");
//                out.println("Event Faculty: ");
//                out.println("Event Location: ");
//                out.println("Event Time: ");
//                out.println("Event Details: ");
//                out.close();//close 
//                }
//                catch(IOException ex){ //if exception occur
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });


        // Add components to the panel
        //panel.add(openFile);
        panel.add(events);
       addBackButton(details, panel);
        addEventsName(panel);
        addEventsFaculty(panel);
        addEventsTime(panel);
        addEventsLocation(panel);
        addEventsDetails(panel);

        // Set layout for the details JFrame and add the panel
        details.setLayout(null);
        details.add(panel);
        details.setVisible(true); // Make the details window visible
    }

    // Method to create a styled button
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(ColorsFonts.darkPurple); // Set button background color
        button.setFont(ColorsFonts.fontButton); // Set font style and size
        button.setForeground(Color.WHITE); // Set font color
        button.setFocusPainted(false); // Disable focus painting
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Set button border

        return button; // Return the styled button
    }
    // Create a 'Back' button
    private void addBackButton(JFrame details, JPanel panel){
        JButton back = createStyledButton("Back");
        back.setBounds(20, 25, 160, 50); // Set position and size of the button
        // Add action listener to the 'Back' button
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                details.dispose(); // Close the details frame when clicked
            }
        });
        panel.add(back);
    }
    
    //Create a label for the faculty name
    private void addEventsFaculty(JPanel panel){
        JLabel facultyName = new JLabel("Faculty: " + event.getFaculty());
        detailsLabel(facultyName,panel, 160);
    }

    //Create a label for the event time
    private void addEventsTime(JPanel panel){
        JLabel eventTime = new JLabel("Event Time: " + event.getEventDate() + ", " + event.getTime());
        detailsLabel(eventTime,panel, 200);;
    }

    //Create a label for the event location
    private void addEventsLocation(JPanel panel){
        JLabel eventLocation = new JLabel("Event Location: " + event.getLocation());
        detailsLabel(eventLocation, panel, 240);
    }
    
    // Add a label for the event location
    public void detailsLabel(JLabel label, JPanel panel,int y){
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(ColorsFonts.fontText);
        label.setForeground(ColorsFonts.darkPurple);
        label.setBounds(100, y, 700, 40); // Set position and size
        panel.add(label);
    }
    
    // Create a label for the event name
    private void addEventsName(JPanel panel){
        JLabel eventsName = new JLabel("Event Name: " + event.getEventName());
        eventsName.setVerticalAlignment(JLabel.TOP); // Align label to the top
        eventsName.setHorizontalAlignment(JLabel.CENTER); // Center align text
        eventsName.setFont(ColorsFonts.fontButton); // Set font style and size
        eventsName.setForeground(ColorsFonts.darkPurple); // Set text color
        eventsName.setBounds(80, 100, 200, 100); // Set position and size of the label
        panel.add(eventsName);
    }

    // Create a JTextPane for additional text
    public void addEventsDetails(JPanel panel){
        JLabel eventLabel = new JLabel("Event Details: ");
        detailsLabel(eventLabel, panel, 280);
        JTextPane textArea = new JTextPane();
        String text = event.getDetails();
        //check if there is a detail for the event
        if (text != null && !text.isEmpty()) {
            textArea.setText(text);
        } else {
            textArea.setText("No details available."); // Fallback text
        }
        textArea.setFont(ColorsFonts.fontText); // Set font style and size
        textArea.setForeground(ColorsFonts.darkPurple); // Set text color
        textArea.setBackground(ColorsFonts.lightPurple); // Set background color
        textArea.setEditable(false);
        panel.add(textArea);
        // Create a JScrollPane to allow scrolling for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(120, 320, 530, 200); // Set position and size
        scrollPane.setForeground(ColorsFonts.lightPurple);
        panel.add(scrollPane);
    }
}
