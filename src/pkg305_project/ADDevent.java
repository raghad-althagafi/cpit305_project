/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Ragha
 */

public class ADDevent {
       public JButton returnButton;
    public JButton addButton;

    public JTextField eventNameField;
    public JTextField dateField;
    public JTextField timeField;
    public JTextField locationField;
    public JTextField collegeField;
     public JTextField publisherField;
    public JTextArea detailsArea;
    public static final Color Dgreen=new Color(5,125,114);
    public Database dbManager;
    private User user;

    public ADDevent(User user) {
        this.user = user;
        // Initialize DatabaseManager
        dbManager = new Database();
        dbManager.createTablesevent(); // Setup tables if they don't exist
    }
    
// Method to create the frame and add components
    public JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setSize(1000, 900);
        frame.getContentPane().setBackground(ColorsFonts.lightPurple);
        
      //  frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
         JLabel e = new JLabel("Add Event");
         e.setFont(ColorsFonts.fontTitle);
        e.setForeground(ColorsFonts.darkPurple);
        e.setBounds(340, 50, 200, 40); 
        frame.add(e);
         
        returnButton = new JButton("Back");
        //(800, 15, 10, 30) (20, 750, 160, 50)
        returnButton.setBounds(20, 25, 160, 50);
        //new Font("Verdana", Font.BOLD, 18)
        returnButton.setFont(ColorsFonts.fontButton);
        returnButton.setForeground(Color.WHITE);
        returnButton.setBackground(ColorsFonts.darkPurple);
        frame.add(returnButton);

        // Create and add label and text field for Event Name
        JLabel eventNameLabel = new JLabel("Event Name:");
        eventNameLabel.setFont(ColorsFonts.fontText);
        eventNameLabel.setForeground(ColorsFonts.darkPurple);
        eventNameLabel.setBounds(140, 150, 150, 30);
       
        frame.add(eventNameLabel);

        eventNameField = new JTextField();
        eventNameField.setBounds(290, 150, 330, 40);
        frame.add(eventNameField);

        // Create and add label and text field for Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(ColorsFonts.fontText);
        dateLabel.setForeground(ColorsFonts.darkPurple);
        dateLabel.setBounds(140, 190, 150, 40);
        frame.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(290, 200, 150, 30);
        frame.add(dateField);

        // Create and add label and text field for Time
        JLabel timeLabel = new JLabel("Time:");
       timeLabel.setFont(ColorsFonts.fontText);
       timeLabel.setForeground(ColorsFonts.darkPurple);
        timeLabel.setBounds(490, 190, 150, 40);
        frame.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(590, 200, 150, 30);
        frame.add(timeField);

        // Create and add label and text field for Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(ColorsFonts.fontText);
        locationLabel.setForeground(ColorsFonts.darkPurple);
        locationLabel.setBounds(140, 250, 150, 30);
        frame.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(290, 250,  330, 40);
        frame.add(locationField);

        // Create and add label and text field for College
        JLabel collegeLabel = new JLabel("College:");
        collegeLabel.setFont(ColorsFonts.fontText);
        collegeLabel.setForeground(ColorsFonts.darkPurple);
        collegeLabel.setBounds(140, 300, 150, 30);
        frame.add(collegeLabel);

        collegeField = new JTextField();
        collegeField.setBounds(290, 300,  330, 40);
        frame.add(collegeField);
        
//        // Create and add label and text field for publisher name
//        JLabel publishereLabel = new JLabel("publisher:");
//        publishereLabel.setFont(ColorsFonts.fontText);
//        publishereLabel.setForeground(ColorsFonts.darkPurple);
//        publishereLabel.setBounds(140, 350, 150, 30);
//        frame.add(publishereLabel);
//
//        publisherField = new JTextField();
//        publisherField.setBounds(290, 350,  330, 40);
//        frame.add(publisherField);

        // Create and add label and text area for Details
        JLabel detailsLabel = new JLabel("Details:");
        detailsLabel.setFont(ColorsFonts.fontText);
       detailsLabel.setForeground(ColorsFonts.darkPurple);
        detailsLabel.setBounds(140, 390, 150, 30);
        frame.add(detailsLabel);

        detailsArea = new JTextArea();
        detailsArea.setBounds(290, 400, 330, 130);
        frame.add(detailsArea);

        // Create and add "Add Event" button
        addButton = new JButton("ADD");
        addButton.setBounds(290, 670, 350, 40);
        addButton.setFont(ColorsFonts.fontText);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(ColorsFonts.darkPurple);
        frame.add(addButton);
        
        // Add event to database when button is clicked
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEventToDatabase();
                Email.sendEmail(dbManager.getAllEmails(),eventNameField.getText(),dateField.getText(),timeField.getText(),locationField.getText());
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(frame, 
                    "The event has been added\n"  );
              frame.dispose();
            }
            
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                frame.dispose();
              
            }
        });
        return frame; // Return the complete frame with components
    }
    public void addEventToDatabase() {
        String eventName = eventNameField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        String location = locationField.getText();
        String college = collegeField.getText();
        String details = detailsArea.getText();
        //String publisher=publisherField.getText();
        String publisher=user.getUsername();

        // Use DatabaseManager to add the event to the database
        dbManager.addEvent(eventName, date,time ,location,college,publisher, details);
        JOptionPane.showMessageDialog(null, "The event has been added to the database!");
    }
    
   
    
    
}