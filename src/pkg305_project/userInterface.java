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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AHC
 */
public class userInterface {

    protected User user;
    protected JPanel eventListPanel;
    protected Database db;
    protected Connection con;
    private JCheckBox[] checkBoxes; // Array to hold faculty checkboxes
    private UpdateEventThread updateEventThread;
    private JFrame userFrame;

    public userInterface(User user) {
        this.user = user;
        // Initialize DatabaseManager
        db = new Database();
        db.createTablesevent(); // Setup tables if they don't exist
        userFrame();

        updateEventThread = new UpdateEventThread(db, this);
        updateEventThread.start();
    }

    public void userFrame() {

        userFrame = new JFrame("Event page");
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
        eventsLabel.setBounds(200, 20, 300, 50);
        rightPanel.add(eventsLabel);

        //"Add new Event" Button (only if user is not a student)
        if (!user.Role()) {
            JButton addEventButton = createStyledButton("Add new Event");
            addEventButton.setFont(ColorsFonts.fontButton);
            addEventButton.setBounds(100, 75, 500, 70);
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
        info.setOpaque(false); // Make the background visible

        // Set the name of the event
        JLabel eventName = new JLabel(event.getEventName());
        eventName.setFont(ColorsFonts.fontButton);
        eventName.setForeground(ColorsFonts.darkPurple);

        // Set the date of the event
        JLabel eventDate = new JLabel(event.getEventDate().toString());
        eventDate.setForeground(ColorsFonts.darkPurple);

        // Set the remaining days for the events
        String date = event.getEventDate();
        int days = remainingDays(date);
        JLabel remainLabel;
        if (days == 0) {
            remainLabel = new JLabel("Event is today");
        } else {
            remainLabel = new JLabel("remain days: " + remainingDays(date) + " days");
        }
        remainLabel.setForeground(ColorsFonts.darkPurple);

        info.add(Box.createVerticalStrut(20)); // Adding spacing
        info.add(eventName);
        info.add(Box.createVerticalStrut(5)); // Adding spacing between labels
        info.add(eventDate);
        info.add(remainLabel);

        // Create Details Button
        JButton detailsButton = createStyledButton("Details");
        detailsButton.setPreferredSize(new Dimension(120, 40)); // Set the prefered size for the panel
        detailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Details details = new Details(event); // Create Details page when pressing the button

                PrintThread thread1 = new PrintThread(event); //thread to print event info in file
                RemainingDaysThread thread2 = new RemainingDaysThread(userInterface.this, event, details);

                thread1.start();
                thread2.start();

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

                    if (choice == 0) { // If "Delete" is selected
                        if (db.deleteEvent(event.getEventName())) { // Use `event.getEventName()` instead of `getEventID()`
                          //  JOptionPane.showMessageDialog(null, "Event deleted successfully.");
                            eventListPanel.remove(panel); // Remove the event panel from the UI
                            eventListPanel.revalidate();
                            eventListPanel.repaint();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to delete the event.");
                        }
                    }
                }
            });

            buttonPanel.add(detailsButton);
            buttonPanel.add(delete);
        } else {
            buttonPanel.add(detailsButton);
        }

        // Assign the layout for the panels
        panel.add(publisher, BorderLayout.WEST); // It will be in the left
        panel.add(info, BorderLayout.CENTER); // It will be in the center
        panel.add(buttonPanel, BorderLayout.SOUTH); // It will be in the down

        return panel;
    }

    // calculate the remaining days for the event
    public int remainingDays(String stringdate) {
        try {
            LocalDate eventDate = LocalDate.parse(stringdate.trim());
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy[-MM][-dd]");
            String date = eventDate.format(format);
            LocalDate today = LocalDate.now();
            long days = ChronoUnit.DAYS.between(today, eventDate);

            // If you want to show negative days for past events
            return (int) days;

            // Or if you want to return 0 for past events
            // return Math.max(0, (int) days);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e);
            return -1;  // or handle the error differently
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
                infoPage iPage = new infoPage(user, db, userFrame);
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

    public void addCheckboxes(JPanel panel) {
        JLabel label = new JLabel("Faculty:");
        label.setFont(ColorsFonts.fontButton);
        label.setForeground(Color.WHITE);
        label.setBounds(20, 100, 200, 50);
        panel.add(label);

        checkBoxes = new JCheckBox[6];
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

            // Add an ActionListener for each checkbox
            checkBoxes[i].addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    // Refresh the event list whenever the selection changes
                    readFromDatabase(db);
                }
            });
        }
        checkBoxes[0].setSelected(true);
    }

    public List<String> getSelectedFaculties() {
        List<String> selectedFaculties = new ArrayList<>();

        // Check if "All Faculty" is selected
        if (checkBoxes[0].isSelected()) {
            selectedFaculties.add("All");
        } else {
            // Add only individually selected faculties
            for (int i = 1; i < checkBoxes.length; i++) {
                if (checkBoxes[i].isSelected()) {
                    selectedFaculties.add(checkBoxes[i].getText());
                }
            }
        }

        // Return null if nothing is selected
        if (selectedFaculties.isEmpty()) {
            return null;
        } else {
            return selectedFaculties;
        }
    }

    public void readFromDatabase(Database db) {
        eventListPanel.removeAll(); // Clear previous event panels
        List<String> selectedFaculties = getSelectedFaculties(); // Get selected faculties

        String query = "SELECT * FROM event";
        // Check if specific faculties are selected
        if (selectedFaculties != null && !selectedFaculties.contains("All")) {
            // Build a WHERE clause to filter events by selected faculties
            StringBuilder filter = new StringBuilder(" WHERE college IN (");
            for (int i = 0; i < selectedFaculties.size(); i++) {
                filter.append("'").append(selectedFaculties.get(i)).append("'");
                if (i < selectedFaculties.size() - 1) {
                    filter.append(", ");
                }
            }
            filter.append(")");
            query += filter.toString(); // Append the filter to the query
        }else if (selectedFaculties == null || selectedFaculties.isEmpty()) {
        // No faculties selected; set query to something that returns no rows
        query = "SELECT * FROM event WHERE 1 = 0";
    }

        // Execute the query and display events
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/KAUEvents", "root", "raghad");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                rs.getString("eventID");
                Event event = new Event(
                        rs.getString("eventName"),
                        rs.getString("eventDate"),
                        rs.getString("eventTime"),
                        rs.getString("location"),
                        rs.getString("college"),
                        rs.getString("publisher"),
                        rs.getString("details"));

                if (remainingDays(event.getEventDate()) >= 0) {
                    boolean isPublisher = user.getUsername().equalsIgnoreCase(event.getUser()) && !user.Role();
                    eventListPanel.add(createEventPanel(event, isPublisher));
                    eventListPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                }
            }
        } catch (SQLException s) {
            System.out.println("SQL statement for retrieving events is not executed!");
            s.printStackTrace();
        }

        // Refresh the display after adding new events
        eventListPanel.revalidate();
        eventListPanel.repaint();
    }

}
