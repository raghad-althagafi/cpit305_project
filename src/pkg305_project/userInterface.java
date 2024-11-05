/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.*;
import javax.swing.border.LineBorder;


/**
 *
 * @author AHC
 */
public class userInterface {
    protected User user;
    protected JPanel eventListPanel;
    protected Database db;
    protected Connection con;

    public userInterface(User user) {
        this.user = user;
        // Initialize DatabaseManager
        db = user.getDb();
        db.createTablesevent(); // Setup tables if they don't exist
        userFrame();
    }
    
    public void userFrame(){
        JFrame userFrame;
        if (user.Role()) {
            userFrame = new JFrame("Student");

       }
       else{
            userFrame = new JFrame("Doctor");
       }
 userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    userFrame.setLayout(null);
    userFrame.setSize(1000, 900);
    userFrame.setLocationRelativeTo(null);

    // Right Panel Setup
    JPanel rightPanel = new JPanel();
    rightPanel.setBackground(ColorsFonts.lightPurple);
    rightPanel.setBounds(300, 0, 700, 900);
    rightPanel.setLayout(null);

    // Title Label
    JLabel eventsLabel = new JLabel("KAU EVENTS");
    eventsLabel.setFont(ColorsFonts.fontTitle);
    eventsLabel.setForeground(ColorsFonts.darkPurple);
    eventsLabel.setBounds(200, 20, 300, 50); // Adjusted size for better layout
    rightPanel.add(eventsLabel);

    // "Add new Event" Button (only if user is not a student)
    if (!user.Role()) {
        JButton addEventButton = createStyledButton("Add new Event");
        addEventButton.setFont(ColorsFonts.fontButton);
        addEventButton.setBounds(100, 75, 500, 70); // Increased size and adjusted position
        rightPanel.add(addEventButton);
       
        //If the addEvent button is clicked
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ADDevent frameCreator = new ADDevent(user);
                JFrame frame = frameCreator.createFrame("Add Event");
                frame.setVisible(true);
            }
        });
    }

    // Event List Panel with ScrollPane
    eventListPanel = new JPanel();
    eventListPanel.setLayout(new BoxLayout(eventListPanel, BoxLayout.Y_AXIS));
    eventListPanel.setBackground(ColorsFonts.lightPurple);

    // ScrollPane for the Event List, positioned below the "Add new Event" button
    JScrollPane scrollPane = new JScrollPane(eventListPanel);
    scrollPane.setBounds(10, 150, 660, 700); // Positioned to leave space for title and button
    scrollPane.getVerticalScrollBar().setUnitIncrement(10);
    rightPanel.add(scrollPane);

    // Add the right panel to the main frame
    userFrame.add(rightPanel);

    // Left Panel
    addLeftPanel(userFrame);

    // Refresh and make visible
    userFrame.revalidate();
    userFrame.repaint();
    userFrame.setVisible(true);

    readFromDatabase(db);
}
    
        // Create the event panel
    private JPanel createEventPanel(Event event, boolean ispublisher) {
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
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align buttons to the left
        buttonPanel.setOpaque(false); // Make the background visible
        
        
        if (ispublisher) {
               // Add the Delete button only if the user is the publisher
    JButton delete = createStyledButton("Delete");
    delete.setPreferredSize(new Dimension(100, 40)); // Set preferred size for delete button

    delete.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"Delete", "No"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Are you sure?",
                    "Confirmation",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (choice == 0) {
                //System.out.println("Item deleted");
            } else {
                //System.out.println("Deletion canceled");
            }
        }
    });
            buttonPanel.add(detailsButton);
            buttonPanel.add(delete); // Add the Delete button to the panel
        }
        else{
            buttonPanel.add(detailsButton);
        }

        

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
        
//            public void addEventToList(Event event) {
////        events.add(event);
//        JPanel eventPanel = createEventPanel(event);
//        eventListPanel.add(eventPanel);
//        eventListPanel.revalidate();
//        eventListPanel.repaint();
//    }
            
            
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
        
            public void readFromDatabase(Database db) {
        String query = "SELECT * FROM event";
        String connectionURL = "jdbc:mysql://localhost:3306/KAUEvents";
        try (Connection conn = DriverManager.getConnection(connectionURL, "root", "raghad");
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
                    //is this user the publisher and not a student
                    if(user.getUsername().equalsIgnoreCase(event.getUser()) && !user.Role()){
                        eventListPanel.add(createEventPanel(event,true));
                        eventListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                    }else{
                    eventListPanel.add(createEventPanel(event,false));
                    eventListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                    }
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement for retrieving users is not executed!");
            s.printStackTrace();
        }
    }


}
