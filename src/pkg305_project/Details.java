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

public class Details {
    // Constructor for the Details class
    public Details() {
        // Create a JFrame for the details window
        JFrame details = new JFrame("Details");
        details.setSize(700, 450); // Set size of the frame
        details.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on exit
        details.setBackground(Color.darkGray); // Set background color (not effective on JFrame)

        // Create a 'Back' button
        JButton back = createStyledButton("Back");
        back.setBounds(10, 25, 140, 40); // Set position and size of the button

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
        events.setFont(new Font("Comic Sans", Font.BOLD, 25)); // Set font style and size
        events.setForeground(Color.WHITE); // Set text color
        events.setBounds(70, 100, 200, 100); // Set position and size of the label

        // Create a label for event details (time)
        JLabel details1 = new JLabel();
        String day = "Sunday"; // Sample event time
        details1.setText("Event Time: " + day);
        details1.setVerticalAlignment(JLabel.CENTER);
        details1.setHorizontalAlignment(JLabel.LEFT);
        details1.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        details1.setForeground(Color.WHITE);
        details1.setBounds(100, 130, 700, 50); // Set position and size

        // Create a JTextPane for additional text
        JTextPane textArea = new JTextPane();
        textArea.setText("Here is a lot of text...\n" +
                "You can write as much as you want here.\n" +
                "Feel free to add paragraphs, details, etc.\n" +
                "This area supports scrolling if the text is long enough.\n" +
                "You can customize this text however you like.\n\n" +
                "End of text.");
        textArea.setFont(new Font("Comic Sans", Font.PLAIN, 20)); // Set font style and size
        textArea.setForeground(Color.WHITE); // Set text color
        textArea.setBackground(Color.darkGray); // Set background color

        // Create a JScrollPane to allow scrolling for the text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(100, 210, 530, 170); // Set position and size

        // Create a label for faculty information
        JLabel location = new JLabel();
        String faculty = "Computing and Information Technology"; // Sample faculty name
        location.setText("Faculty: " + faculty);
        location.setVerticalAlignment(JLabel.CENTER);
        location.setHorizontalAlignment(JLabel.LEFT);
        location.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        location.setForeground(Color.WHITE);
        location.setBounds(100, 160, 700, 50); // Set position and size

        // Create a JPanel to hold all components
        JPanel panel = new JPanel();
        panel.setLayout(null); // Use null layout for manual positioning
        panel.setBounds(0, 0, 1000, 900); // Set position and size
        panel.setBackground(Color.darkGray); // Set panel background color

        // Add components to the panel
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
        button.setBackground(new Color(8, 78, 65)); // Set button background color
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20)); // Set font style and size
        button.setForeground(Color.WHITE); // Set font color
        button.setFocusPainted(false); // Disable focus painting
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Set button border

        return button; // Return the styled button
    }
}
