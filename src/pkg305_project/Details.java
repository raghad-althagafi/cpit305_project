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
    // Constructor for the Details class
    public Details() {
        // Create a JFrame for the details window
        JFrame details = new JFrame("Details");
        details.setSize(800, 600); // Set size of the frame
        details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        details.setBackground(ColorsFonts.lightPurple); // Set background color (not effective on JFrame)

        // Create a 'Back' button
        JButton back = createStyledButton("Back");
        //(10, 25, 140, 40)
        back.setBounds(20, 25, 160, 50); // Set position and size of the button

        // Add action listener to the 'Back' button
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                details.dispose(); // Close the details frame when clicked
            }
        });

        // Create a label for the event name
        JLabel events = new JLabel();
        events.setText("Event Name");
        events.setVerticalAlignment(JLabel.TOP); // Align label to the top
        events.setHorizontalAlignment(JLabel.CENTER); // Center align text
        events.setFont(ColorsFonts.fontButton); // Set font style and size
        events.setForeground(ColorsFonts.darkPurple); // Set text color
        events.setBounds(80, 100, 200, 100); // Set position and size of the label

        // Create a label for event details (time)
        JLabel details1 = new JLabel();
        String day = "Sunday"; // Sample event time
        details1.setText("Event Time: " + day);
        details1.setVerticalAlignment(JLabel.CENTER);
        details1.setHorizontalAlignment(JLabel.LEFT);
        details1.setFont(ColorsFonts.fontText);
        details1.setForeground(ColorsFonts.darkPurple);
        details1.setBounds(100, 160, 700, 100); // Set position and size

        // Create a JTextPane for additional text
        JTextPane textArea = new JTextPane();
        textArea.setText("Here is a lot of text...\n" +
                "You can write as much as you want here.\n" +
                "Feel free to add paragraphs, details, etc.\n" +
                "This area supports scrolling if the text is long enough.\n" +
                "You can customize this text however you like.\n\n" +
                "End of text.");
        textArea.setFont(ColorsFonts.fontText); // Set font style and size
        textArea.setForeground(ColorsFonts.darkPurple); // Set text color
        textArea.setBackground(ColorsFonts.lightPurple); // Set background color

        // Create a JScrollPane to allow scrolling for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(100, 260, 530, 170); // Set position and size
        scrollPane.setForeground(ColorsFonts.lightPurple);

        // Create a label for faculty information
        JLabel location = new JLabel();
        String faculty = "Computing"; // Sample faculty name
        location.setText("Faculty: " + faculty);
        location.setVerticalAlignment(JLabel.CENTER);
        location.setHorizontalAlignment(JLabel.LEFT);
        location.setFont(ColorsFonts.fontText);
        location.setForeground(ColorsFonts.darkPurple);
        location.setBounds(100, 140, 700, 50); // Set position and size

        // Create a JPanel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for manual positioning
        panel.setBounds(0, 0, 1000, 900); // Set position and size
        panel.setBackground(ColorsFonts.lightPurple); // Set panel background color
        
         //open in file button
        JButton openFile = createStyledButton("Open in File");
        openFile.setBounds(250, 450, 200, 50); // Set position and size of the button
        
        // Add action listener to the 'open in file' button
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                PrintWriter out = new PrintWriter(new FileWriter(".\\Event.txt")); //write event information in a file
                out.println("Event Name: ");// write detaila in file
                out.println("Event Date: ");
                out.println("Event Faculty: ");
                out.println("Event Location: ");
                out.println("Event Time: ");
                out.println("Event Details: ");
                out.close();//close 
                }
                catch(IOException ex){ //if exception occur
                    System.out.println(ex.getMessage());
                }
            }
        });


        // Add components to the panel
        panel.add(openFile);
        panel.add(back);
        panel.add(events);
        panel.add(details1);
        panel.add(location);
        panel.add(scrollPane); // Add scroll pane for text area

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
}
