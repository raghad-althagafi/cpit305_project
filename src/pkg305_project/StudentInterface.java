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
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author shaha
 */
public class StudentInterface {

    private User user;
    private JPanel eventListPanel;
    public Database db;
    private Connection con;

    public StudentInterface(User user) {
        this.user = user;
        // Initialize DatabaseManager
        db = user.getDb();
        db.createTablesevent(); // Setup tables if they don't exist

        // Set up the main frame for the student interface
        JFrame studentFrame = new JFrame("Student");
        studentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application on exit
        studentFrame.setLayout(null); // Use null layout for manual positioning
        studentFrame.setSize(1000, 900); // Set frame size
        studentFrame.setVisible(true); // Make the frame visible
        studentFrame.setLocationRelativeTo(null);

        // Right Panel Setup
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(ColorsFonts.lightPurple);
        rightPanel.setBounds(300, 0, 700, 900); // Set position and size of the right panel
        rightPanel.setLayout(null); // Use null layout for manual positioning

        // Create a main event label
        JLabel eventsLabel = new JLabel("KAU EVENTS");
        eventsLabel.setFont(ColorsFonts.fontTitle);
        eventsLabel.setForeground(ColorsFonts.darkPurple);
        eventsLabel.setBounds(200, 20, 300, 100); // Set position and size of the label
        rightPanel.add(eventsLabel); //add the events label to the right label

        // Create panel for all events
        eventListPanel = new JPanel();
        eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS)); //make the layout for y-axis
        eventListPanel.setBackground(ColorsFonts.lightPurple);

        // Create scrollpane for the events
        JScrollPane scrollPane = new JScrollPane(eventListPanel);
        scrollPane.setBounds(10, 100, 660, 700); // Set position and size of the scrollpane
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); //
        rightPanel.add(scrollPane);

        // Make the left panel
        addLeftPanel(studentFrame);
        // Add the right panel to the frame
        studentFrame.add(rightPanel);

        readFromDatabase(db);
        // addSampleEvents();

    }

    // Create the event panel
    private JPanel createEventPanel(Event event) {
        // Set the main panel
        JPanel panel = new JPanel(new BorderLayout(50, 3)); // Create a panel with layout
        panel.setBackground(ColorsFonts.lightPurple);
        panel.setBorder(new LineBorder(ColorsFonts.midPurpule, 1)); // Add border to the event panel
        panel.setMaximumSize(new Dimension(640, 170)); // Set the max size for the panel
        panel.setPreferredSize(new Dimension(640, 150)); // Set the prefered size for the panel

        // Set panel for the one who publish the event
        JPanel publisher = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        publisher.setOpaque(false); // Make the background visible
        JLabel publisherLabel = new JLabel("\uD83D\uDC64 " + event.getUser());
        publisherLabel.setFont(ColorsFonts.fontButton);
        publisherLabel.setForeground(ColorsFonts.darkPurple);
        publisher.add(publisherLabel); // Add the label to the panel

        // Set the information event
        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS)); //make the layout for y-axis
        //info.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        info.setOpaque(false); // Make the background visible

        // Set the name of the event
        JLabel eventName = new JLabel(event.getEventName());
        eventName.setFont(ColorsFonts.fontButton);
        eventName.setForeground(ColorsFonts.darkPurple);
        //nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Set the date of the event
        //JLabel eventDate = new JLabel(SDF.format(event.getEventDate()));
        JLabel eventDate = new JLabel(event.getEventDate().toString());
        eventDate.setForeground(ColorsFonts.darkPurple);
        //dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        //JLabel dateLabel = new JLabel(SDF.format(event.getEventDate()));
        // Set the remaining days for the events
        String date =  event.getEventDate();
        //System.out.println((event.getEventDate().compareTo("2024-11-5")));
        JLabel remainLabel = new JLabel("remain days: " + remainingDays(date) + " days");
        remainLabel.setForeground(ColorsFonts.darkPurple);
        //dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        info.add(Box.createVerticalStrut(20)); // Adding spacing
        info.add(eventName);
        info.add(Box.createVerticalStrut(5)); // Adding spacing between labels
        info.add(eventDate);
        //infoPanel.add(Box.createVerticalStrut(5)); // Adding spacing between labels
        info.add(remainLabel);

        // Create Details Button
        JButton detailsButton = createStyledButton("Details");
        detailsButton.setPreferredSize(new Dimension(120, 40)); // Set the prefered size for the panel
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Details details = new Details(event); // Create Details page when pressing the button
                details.DetailPage();
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //make the button in the right
        buttonPanel.setOpaque(false); // Make the background visible in the down of the box
        buttonPanel.add(detailsButton);

        // Assign the layout for the panels
        panel.add(publisher, BorderLayout.WEST); // It will be in the left
        panel.add(info, BorderLayout.CENTER); // It will be in the center
        panel.add(buttonPanel, BorderLayout.SOUTH); // It will be in the down

        return panel;
    }

    // calculate the remaining days for the event
    private int remainingDays(String stringdate) {
        try {
        LocalDate eventDate = LocalDate.parse(stringdate.trim());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = eventDate.format(format);
        LocalDate today = LocalDate.now();
        long days = ChronoUnit.DAYS.between(today, eventDate);
        
        // If you want to show negative days for past events
        return (int) days;
        
        // Or if you want to return 0 for past events
        // return Math.max(0, (int) days);
    } catch (DateTimeParseException e) {
        System.out.println("Error parsing date: " + e);
        return 0;  // or handle the error differently
    }
    }
    
    // Method to create a styled button
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(ColorsFonts.darkPurple); // Set button background color
        button.setFont(ColorsFonts.smallText); // Set font style
        button.setForeground(Color.WHITE); // Set font color
        button.setFocusPainted(false); // Remove focus painting
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Set button border
        //button.setBorderPainted(false);
        return button;
    }

    // Method to add checkboxes to the given panel
    public static void addCheckboxes(JPanel panel) {
        // Add the label for the checkboxes
        JLabel label = new JLabel("Faculty:");
        label.setFont(ColorsFonts.fontButton);
        label.setForeground(Color.WHITE);
        label.setBounds(20, 100, 200, 50);
        panel.add(label);

        // Create checkboxes
        JCheckBox[] checkBoxes = new JCheckBox[6];
        String[] labels = {"All Faculty", "Econ & Admin", "Engineering", "Computing", "Law", "Science"};
        int yPosition = 150;

        for (int i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i] = new JCheckBox(labels[i]);
            checkBoxes[i].setFont(ColorsFonts.fontButton);
            checkBoxes[i].setForeground(Color.WHITE);
            checkBoxes[i].setBackground(ColorsFonts.midPurpule);
            checkBoxes[i].setBounds(20, yPosition, 300, 50);
            yPosition += 50;
            panel.add(checkBoxes[i]);
        }
    }

    public void addEventToList(Event event) {
//        events.add(event);
        JPanel eventPanel = createEventPanel(event);
        eventListPanel.add(eventPanel);
        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

    public void addLeftPanel(JFrame frame) {
        // Create a left panel with checkboxes
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(ColorsFonts.midPurpule); // Set background color
        leftPanel.setBounds(0, 0, 300, 900);
        leftPanel.setLayout(null); // Use null layout for manual positioning
        addCheckboxes(leftPanel); // Add checkboxes to the left panel

        // Create a user button with the student's name
        JButton userButton = createStyledButton("\uD83D\uDC64 " + user.getUsername());
        userButton.setBounds(20, 30, 160, 50); // Set position and size of the button
        leftPanel.add(userButton); // Add the button to the left panel

        //It will open the user page after click the button
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoPage iPage = new infoPage(user);
                iPage.showPage();
            }
        });

        //Create an exit button
        JButton exitButton = createStyledButton("Exit");
        exitButton.setBounds(20, 750, 160, 50); // Set position and size of the button
        leftPanel.add(exitButton); // Add the button to the left panel
        exitButton.addActionListener(e -> System.exit(0));

        frame.add(leftPanel);
    }

    public void readFromDatabase(Database db) {
        String query = "SELECT * FROM events";
        String connectionURL = "jdbc:mysql://localhost:3306/KAUEvents";
        try (Connection conn = DriverManager.getConnection(connectionURL, "root", "shahad");
                Statement st = conn.createStatement(); 
            ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                // Assuming the events table
                rs.getString("eventID");
                Event event = new Event(
                        rs.getString("eventName"),
                        rs.getString("eventDate"),
                        rs.getString("eventTime"),
                        rs.getString("location"),
                        rs.getString("college"),
                        rs.getString("publisher"),
                        rs.getString("details"));

                // Add the event and check if it didn't end
                if (remainingDays(event.getEventDate()) >= 0) {
                    eventListPanel.add(createEventPanel(event));
                    eventListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement for retrieving users is not executed!");
            s.printStackTrace();
        }
    }
}
