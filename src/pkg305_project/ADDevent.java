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
    public JTextArea detailsArea;
    public static final Color Dgreen=new Color(5,125,114);
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
         
        returnButton = new JButton("Return");
        returnButton.setBounds(800, 15, 120, 30);
        returnButton.setFont(new Font("Verdana", Font.BOLD, 18));
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
        dateLabel.setBounds(140, 200, 150, 40);
        frame.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(290, 200, 150, 30);
        frame.add(dateField);

        // Create and add label and text field for Time
        JLabel timeLabel = new JLabel("Time:");
       timeLabel.setFont(ColorsFonts.fontText);
       timeLabel.setForeground(ColorsFonts.darkPurple);
        timeLabel.setBounds(490, 200, 150, 40);
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              JOptionPane.showMessageDialog(frame, 
                    "The event has been added\n"  );
            }
        });
        return frame; // Return the complete frame with components
    }
    
    
}
