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
import javax.swing.border.LineBorder;
/**
 *
 * @author shaha
 */
public class StudentInterface {
    private User user;
   
    // Constructor for the StudentInterface class
    public StudentInterface(User user) {
        
        this.user = user;
        // Sample data for the event
        String name = "Shahad";
        String eventName = "Event Name";
        String eventStart = "Day of start";

        // Create a main event label
        JLabel events = new JLabel("KAU EVENTS");
        events.setVerticalAlignment(JLabel.TOP);
        events.setHorizontalAlignment(JLabel.CENTER);
        events.setFont(ColorsFonts.fontTitle);
        events.setForeground(ColorsFonts.darkPurple);
        events.setVisible(true);
        events.setBounds(200, 20, 300, 100); // Set position and size of the label

        // Create panels for events and lines
        JPanel E1 = createStyledPanel(name, eventName, eventStart, 150);
        //JPanel line1 = createLine(302);
        JPanel E2 = createStyledPanel(name, eventName, eventStart, 305);
        //JPanel line2 = createLine(457);
        JPanel E3 = createStyledPanel(name, eventName, eventStart, 460);
        //JPanel line3 = createLine(612);
        JPanel E4 = createStyledPanel(name, eventName, eventStart, 615);
        //JPanel line4 = createLine(765);

        // Create a left panel with checkboxes
        JPanel panel = new JPanel();
        panel.setBackground(ColorsFonts.midPurpule); // Set background color
        panel.setBounds(0, 0, 300, 900);
        panel.setLayout(null); // Use null layout for manual positioning
        addCheckboxes(panel); // Add checkboxes to the left panel

        // Create the main content panel for events
        JPanel panel2 = new JPanel();
        panel2.setBackground(ColorsFonts.lightPurple);
        panel2.setBounds(300, 0, 700, 900);
        panel2.setLayout(null); // Use null layout for manual positioning
        panel2.add(events); // Add event label
        panel2.add(E1); // Add event panels
        //panel2.add(line1);
        panel2.add(E2);
        //panel2.add(line2);
        panel2.add(E3);
        //panel2.add(line3);
        panel2.add(E4);
        //panel2.add(line4);

        // Create a user button with the student's name
        JButton userButton = createStyledButton("\uD83D\uDC64 " + name);
        userButton.setBounds(20, 30, 160, 50); // Set position and size of the button
        panel.add(userButton); // Add the button to the left panel
        
        userButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
               infoPage iPage = new infoPage(user);
               iPage.showPage();
               
            }
        });
        
        // Create an exit button
        JButton exitButton = createStyledButton("Exit");
        exitButton.setFont(ColorsFonts.fontButton);
        exitButton.setBounds(20, 750, 160, 50); // Set position and size of the button
        panel.add(exitButton); // Add the button to the left panel
        

        // Set up the main frame for the student interface
        JFrame studentFrame = new JFrame("Student");
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on exit
        studentFrame.setLayout(null); // Use null layout for manual positioning
        studentFrame.setSize(1000, 900); // Set frame size
        studentFrame.add(panel); // Add left panel
        studentFrame.add(panel2); // Add right panel with events
        studentFrame.setVisible(true); // Make the frame visible
        
        exitButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
               studentFrame.dispose();
               
            }
        });
    }
    
    // Method to create a styled button
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(ColorsFonts.darkPurple); // Set button background color
        button.setFont(ColorsFonts.smallText); // Set font style
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

        JButton viewButton = createStyledButton("Details");
        viewButton.setBounds(520, 90, 100, 40); // Set position and size of the view button
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Details s = new Details(); // Open details frame on button click
            }
        });

        // Create event name label
        JLabel eventLabel = new JLabel(eventName);
        eventLabel.setFont(ColorsFonts.fontButton);
        eventLabel.setForeground(ColorsFonts.darkPurple);
        eventLabel.setBounds(180, 8, 700, 50); // Set position and size

        // Create event start label
        JLabel detailsLabel = new JLabel(eventStart);
        detailsLabel.setFont(ColorsFonts.fontText);
        detailsLabel.setForeground(ColorsFonts.darkPurple);
        detailsLabel.setBounds(180, 55, 700, 50); // Set position and size

        // Create the event panel
        JPanel eventPanel = new JPanel();
        eventPanel.setBackground(ColorsFonts.lightPurple);
        eventPanel.setBounds(10, y, 660, 150); // Set position and size
        eventPanel.setBorder(new LineBorder(ColorsFonts.darkPurple));
        eventPanel.setLayout(null); // Use null layout for manual positioning
        eventPanel.add(userButton); // Add user button
        eventPanel.add(viewButton); // Add view button
        eventPanel.add(eventLabel); // Add event name label
        eventPanel.add(detailsLabel); // Add event start label

        return eventPanel; // Return the styled event panel
    }
/*
    // Method to create a line (divider) panel
    public static JPanel createLine(int y) {
        JPanel line = new JPanel();
        line.setBackground(Color.BLACK); // Set line color
        line.setBounds(0, y, 700, 1); // Set position and size
        return line; // Return the line panel
    }
*/

    // Method to add checkboxes to the given panel
    public static void addCheckboxes(JPanel panel) {
        // Create faculty checkboxes
        Checkbox FEA = new Checkbox("Econ & Admin");
        Checkbox Eng = new Checkbox("Engineering");
        Checkbox FCIT = new Checkbox("Computing");
        Checkbox Law = new Checkbox("Law");
        Checkbox Science = new Checkbox("Science");

        // Create label for faculty section
        JLabel label = new JLabel("Faculty:");
        //new Font("Comic Sans", Font.PLAIN, 20)
        label.setFont(ColorsFonts.fontButton);
        label.setForeground(Color.WHITE);
        label.setBounds(20, 150, 200, 50); // Set position and size

        // Set bounds for each checkbox
        FEA.setBounds(20, 200, 300, 30);
        Eng.setBounds(20, 240, 300, 30);
        FCIT.setBounds(20, 280, 300, 30);
        Law.setBounds(20, 320, 300, 30);
        Science.setBounds(20, 360, 300, 30);

        // Set font and color for checkboxes
        //new Font("Comic Sans", Font.PLAIN, 15)
        Font checkboxFont = ColorsFonts.fontText;
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
