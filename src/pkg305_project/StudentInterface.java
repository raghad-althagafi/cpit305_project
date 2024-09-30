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
/**
 *
 * @author shaha
 */
public class StudentInterface {
    // Constructor for the StudentInterface class
    public StudentInterface() {
        // Sample data for the event
        String name = "Shahad";
        String eventName = "Event Name";
        String eventStart = "Day of start";

        // Create a main event label
        JLabel events = new JLabel("KAU EVENTS");
        events.setVerticalAlignment(JLabel.TOP);
        events.setHorizontalAlignment(JLabel.CENTER);
        events.setFont(new Font("Comic Sans", Font.BOLD, 35));
        events.setForeground(Color.WHITE);
        events.setVisible(true);
        events.setBounds(200, 30, 300, 100); // Set position and size of the label

        // Create panels for events and lines
        JPanel E1 = createStyledPanel(name, eventName, eventStart, 150);
        JPanel line1 = createLine(302);
        JPanel E2 = createStyledPanel(name, eventName, eventStart, 305);
        JPanel line2 = createLine(457);
        JPanel E3 = createStyledPanel(name, eventName, eventStart, 460);
        JPanel line3 = createLine(612);
        JPanel E4 = createStyledPanel(name, eventName, eventStart, 615);
        JPanel line4 = createLine(765);

        // Create a left panel with checkboxes
        JPanel panel = new JPanel();
        panel.setBackground(new Color(8, 78, 65)); // Set background color
        panel.setBounds(0, 0, 300, 900);
        panel.setLayout(null); // Use null layout for manual positioning
        addCheckboxes(panel); // Add checkboxes to the left panel

        // Create the main content panel for events
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.DARK_GRAY);
        panel2.setBounds(300, 0, 700, 900);
        panel2.setLayout(null); // Use null layout for manual positioning
        panel2.add(events); // Add event label
        panel2.add(E1); // Add event panels
        panel2.add(line1);
        panel2.add(E2);
        panel2.add(line2);
        panel2.add(E3);
        panel2.add(line3);
        panel2.add(E4);
        panel2.add(line4);

        // Create a user button with the student's name
        JButton userButton = createStyledButton("\uD83D\uDC64 " + name);
        userButton.setBounds(20, 30, 200, 50); // Set position and size of the button
        panel.add(userButton); // Add the button to the left panel

        // Set up the main frame for the student interface
        JFrame studentFrame = new JFrame("Student");
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on exit
        studentFrame.setLayout(null); // Use null layout for manual positioning
        studentFrame.setSize(1000, 900); // Set frame size
        studentFrame.add(panel); // Add left panel
        studentFrame.add(panel2); // Add right panel with events
        studentFrame.setVisible(true); // Make the frame visible
    }

    // Method to create a styled button
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(8, 78, 65)); // Set button background color
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20)); // Set font style
        button.setForeground(Color.WHITE); // Set font color
        button.setFocusPainted(false); // Remove focus painting
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Set button border
        return button; // Return the styled button
    }

    // Method to create a styled panel for an event
    public static JPanel createStyledPanel(String name, String eventName, String eventStart, int y) {
        JButton userButton = createStyledButton("\uD83D\uDC64 " + name);
        userButton.setBounds(10, 10, 150, 50); // Set position and size of the user button
        userButton.setEnabled(false); // Disable the button

        JButton viewButton = createStyledButton("View Details");
        viewButton.setBounds(530, 90, 140, 40); // Set position and size of the view button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Details(); // Open details frame on button click
            }
        });

        // Create event name label
        JLabel eventLabel = new JLabel(eventName);
        eventLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        eventLabel.setForeground(Color.WHITE);
        eventLabel.setBounds(180, 8, 700, 50); // Set position and size

        // Create event start label
        JLabel detailsLabel = new JLabel(eventStart);
        detailsLabel.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        detailsLabel.setForeground(Color.WHITE);
        detailsLabel.setBounds(180, 55, 700, 50); // Set position and size

        // Create the event panel
        JPanel eventPanel = new JPanel();
        eventPanel.setBackground(Color.DARK_GRAY);
        eventPanel.setBounds(0, y, 700, 150); // Set position and size
        eventPanel.setLayout(null); // Use null layout for manual positioning
        eventPanel.add(userButton); // Add user button
        eventPanel.add(viewButton); // Add view button
        eventPanel.add(eventLabel); // Add event name label
        eventPanel.add(detailsLabel); // Add event start label

        return eventPanel; // Return the styled event panel
    }

    // Method to create a line (divider) panel
    public static JPanel createLine(int y) {
        JPanel line = new JPanel();
        line.setBackground(Color.BLACK); // Set line color
        line.setBounds(0, y, 700, 1); // Set position and size
        return line; // Return the line panel
    }

    // Method to add checkboxes to the given panel
    public static void addCheckboxes(JPanel panel) {
        // Create faculty checkboxes
        Checkbox FEA = new Checkbox("Economics and Administration");
        Checkbox Eng = new Checkbox("Engineering");
        Checkbox FCIT = new Checkbox("Computing and Information Technology");
        Checkbox Law = new Checkbox("Law");
        Checkbox Science = new Checkbox("Science");

        // Create label for faculty section
        JLabel label = new JLabel("Faculty:");
        label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        label.setForeground(Color.WHITE);
        label.setBounds(20, 100, 200, 50); // Set position and size

        // Set bounds for each checkbox
        FEA.setBounds(20, 150, 300, 30);
        Eng.setBounds(20, 190, 300, 30);
        FCIT.setBounds(20, 230, 300, 30);
        Law.setBounds(20, 270, 300, 30);
        Science.setBounds(20, 310, 300, 30);

        // Set font and color for checkboxes
        Font checkboxFont = new Font("Comic Sans", Font.PLAIN, 15);
        FEA.setFont(checkboxFont); FEA.setForeground(Color.WHITE);
        Eng.setFont(checkboxFont); Eng.setForeground(Color.WHITE);
        FCIT.setFont(checkboxFont); FCIT.setForeground(Color.WHITE);
        Law.setFont(checkboxFont); Law.setForeground(Color.WHITE);
        Science.setFont(checkboxFont); Science.setForeground(Color.WHITE);

        // Add components to the panel
        panel.add(label);
        panel.add(FEA);
        panel.add(Eng);
        panel.add(FCIT);
        panel.add(Law);
        panel.add(Science);
    }
}
