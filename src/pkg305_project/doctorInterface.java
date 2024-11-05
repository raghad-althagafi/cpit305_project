/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author AHC
 */
public class doctorInterface extends userInterface{

   // private User user;

    public doctorInterface(User user) {
super(user);
    }

    public void showPage() {

        String name = user.getUsername();
        String EventName = "Event Name";
        String EventStart = "Day of start";

        //lables
        JLabel events = new JLabel("KAU EVENTS");
        //set font,color and its position
        events.setFont(ColorsFonts.fontTitle);
        events.setForeground(ColorsFonts.darkPurple);
        events.setBounds(200, 20, 300, 100);

        //create the events


        //create the left panel
        JPanel panel = new JPanel();
        panel.setBackground(ColorsFonts.midPurpule);
        panel.setBounds(0, 0, 300, 900);
        panel.setLayout(null); 
        addCheckboxes(panel); // Add checkboxes to the left panel
        //exit button
        JButton exit = createStyledButton("Exit");
        exit.setForeground(Color.white);
        exit.setBounds(20, 750, 160, 50);
        exit.setFont(ColorsFonts.fontButton);

        //create the right panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(ColorsFonts.lightPurple);
        panel2.setBounds(300, 0, 700, 900);
        panel2.setLayout(null);

        //add the components to the right panel
        JButton addEventButton = createStyledButton("Add new Event");
        addEventButton.setBounds(90, 100, 500, 50);

        panel2.add(addEventButton);
        panel2.add(events);

        //panel2.add(line);
        //panel2.add(line2);
        //panel2.add(line3);
        //panel2.add(line4);

        //add the components to the left panel
        JButton userButton = createStyledButton("\uD83D\uDC64 " + name);

        panel.add(exit);
        userButton.setBounds(20, 30, 160, 50);
        panel.add(userButton);

        //create the frame of the home page
        Frame Doctor = new Frame("Home page");

        Doctor.setLayout(null);
        Doctor.add(panel);
        Doctor.add(panel2);

        //If the addEvent button is clicked
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ADDevent frameCreator = new ADDevent(user);
                JFrame frame = frameCreator.createFrame("Add Event");
                frame.setVisible(true);
            }
        });

        //If the user button is clicked
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Doctor.dispose();
                infoPage iPage = new infoPage(user);
                iPage.showPage();

            }
        });

        //If the exit button is clicked
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor.dispose();
            }
        });

    }

//create button and set its attribute
    public static JButton createStyledButton(String text) {

        JButton button = new JButton(text);
        button.setBackground(ColorsFonts.darkPurple);
        button.setFont(ColorsFonts.smallText);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        //add the details page
        if (text.equals("Details")) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //new Details(); // Open details frame on button click
                }
            });
        }

        return button;
    }

    public JPanel createStyledPanel(String name, String EventName, String EventStart, int y, boolean isCreator) {
        //buttons
        JButton Ebutton1 = createStyledButton("\uD83D\uDC64 " + name);
        Ebutton1.setBounds(10, 10, 150, 50);
        Ebutton1.setEnabled(false);

        JButton view1 = createStyledButton("Details");
        view1.setBounds(530, 90, 100, 40);

        JLabel el1 = new JLabel();
        el1.setText(EventName);
        el1.setFont(ColorsFonts.fontButton);
        el1.setForeground(ColorsFonts.darkPurple);
        el1.setVisible(true);
        el1.setBounds(180, 8, 700, 50);

        JLabel details1 = new JLabel();
        details1.setText(EventStart);
        details1.setFont(ColorsFonts.fontText);
        details1.setForeground(ColorsFonts.darkPurple);
        details1.setVisible(true);
        details1.setBounds(180, 55, 700, 50);

        JPanel E1 = new JPanel();
        E1.setBackground(ColorsFonts.lightPurple);
        E1.setBounds(10, y, 660, 150);
        E1.setBorder(new LineBorder(ColorsFonts.darkPurple));
        E1.setLayout(null);
        E1.add(Ebutton1);
        E1.add(view1);
        E1.add(el1);
        E1.add(details1);

        //if the event information is made by this account
        if (isCreator) {
            JButton delete = createStyledButton("Delete");
            delete.setBounds(400, 90, 120, 40);
            E1.add(delete);

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
                        // System.out.println("Item deleted");
                    } else {
                        //  System.out.println("Deletion canceled");
                    }

                }
            });

        }

        return E1;

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

    public static JPanel lines(int y) {
        JPanel line = new JPanel();
        line.setBackground(Color.BLACK);
        line.setBounds(0, y, 700, 1);
        line.setLayout(null);
        return line;
    }

}
