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
        frame.getContentPane().setBackground(Color.GRAY);
        
      //  frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        JLabel e = new JLabel("Add Event");
         e.setFont(new Font("Verdana", Font.BOLD, 30));
        e.setForeground(Color.WHITE);
        e.setBounds(340, 50, 200, 40); 
        frame.add(e);
        
        returnButton = new JButton("Return");
        returnButton.setBounds(800, 15, 100, 30);
        returnButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        returnButton.setForeground(Color.WHITE);
        returnButton.setBackground(Dgreen);
        frame.add(returnButton);

        // Create and add label and text field for Event Name
        JLabel eventNameLabel = new JLabel("Event Name:");
        eventNameLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        eventNameLabel.setForeground(Color.WHITE);
        eventNameLabel.setBounds(140, 150, 150, 30);
       
        frame.add(eventNameLabel);

        eventNameField = new JTextField();
        eventNameField.setBounds(290, 150, 300, 30);
        frame.add(eventNameField);

        // Create and add label and text field for Date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setBounds(140, 200, 150, 30);
        frame.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(290, 200, 150, 30);
        frame.add(dateField);

        // Create and add label and text field for Time
        JLabel timeLabel = new JLabel("Time:");
       timeLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
       timeLabel.setForeground(Color.WHITE);
        timeLabel.setBounds(490, 200, 150, 30);
        frame.add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(590, 200, 150, 30);
        frame.add(timeField);

        // Create and add label and text field for Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        locationLabel.setForeground(Color.WHITE);
        locationLabel.setBounds(140, 250, 150, 30);
        frame.add(locationLabel);

        locationField = new JTextField();
        locationField.setBounds(290, 250, 300, 30);
        frame.add(locationField);

        // Create and add label and text field for College
        JLabel collegeLabel = new JLabel("College:");
        collegeLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        collegeLabel.setForeground(Color.WHITE);
        collegeLabel.setBounds(140, 300, 150, 30);
        frame.add(collegeLabel);

        collegeField = new JTextField();
        collegeField.setBounds(290, 300, 300, 30);
        frame.add(collegeField);

        // Create and add label and text area for Details
        JLabel detailsLabel = new JLabel("Details:");
        detailsLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
       detailsLabel.setForeground(Color.WHITE);
        detailsLabel.setBounds(140, 390, 150, 30);
        frame.add(detailsLabel);

        detailsArea = new JTextArea();
        detailsArea.setBounds(290, 400, 300, 100);
        frame.add(detailsArea);

        // Create and add "Add Event" button
        addButton = new JButton("ADD");
        addButton.setBounds(340, 600, 200, 40);
        addButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Dgreen);
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
