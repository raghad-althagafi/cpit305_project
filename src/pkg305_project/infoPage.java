/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author AHC
 */
public class infoPage {
    private User user;

    

    public infoPage(User user) {
        this.user = user;
    }
    
    
//setters and getters
    public User getUser() {
        return user;
    }
    

    public void setUser(User user) {
        this.user = user;
    }
    
    
//create profile page
    public void showPage() {
        
        //create frame and change its color
        Frame f = new Frame("Profile");
        f.getContentPane().setBackground(Color.darkGray);
        f.setLayout(null);
        
        //create font
        Font newFont = new Font("Serif", Font.BOLD, 40);
        Font newFont2 = new Font("Serif", Font.BOLD, 30);
        
                    // Load the image
//            ImageIcon imageIcon = new ImageIcon("user-png-33846.png");
//
//            JLabel imageLabel = new JLabel(imageIcon);
//            f.add(imageLabel);

        //labels
        JLabel userName = new JLabel("Username");
        userName.setForeground(Color.WHITE);
        f.add(userName);

        JLabel userEmail = new JLabel("Email");
        userEmail.setForeground(Color.WHITE);
        f.add(userEmail);

        JLabel userPassword = new JLabel("Password");
        userPassword.setForeground(Color.WHITE);
        f.add(userPassword);
        
        //label's positions
        userName.setBounds(235, 5, 500, 500);
        userEmail.setBounds(235, 60, 500, 500);
        userPassword.setBounds(235, 120, 500, 500);

        //textFields
        JTextField nameTF = new JTextField(user.getName());
        JTextField EmailTF = new JTextField(user.getEmail());
        JTextField PasswordTF = new JTextField(String.valueOf(user.getPassword()));
       
       
        f.add(nameTF);
        f.add(EmailTF);
        f.add(PasswordTF);
        
        //textField's positions
        nameTF.setBounds(450, 240, 220, 40); 
        EmailTF.setBounds(450, 300, 220, 40);
        PasswordTF.setBounds(450, 360, 220, 40);
        
        //buttons
        JButton Cbutton = new JButton("Confirm");
        JButton Bbutton = new JButton("Back");
        JButton logButton = new JButton("Log out");
        JButton DelButton = new JButton("Delete the account");
        
        Cbutton.setBackground(new Color(5, 125, 114));
        Cbutton.setForeground(Color.WHITE);
        Bbutton.setBackground(new Color(5, 125, 114));
        Bbutton.setForeground(Color.WHITE);
        logButton.setBackground(new Color(5, 125, 114));
        logButton.setForeground(Color.WHITE);
        DelButton.setBackground(Color.RED);
        DelButton.setForeground(Color.WHITE);
        
        //button's positions
        Cbutton.setBounds(235, 460, 500, 50); 
        logButton.setBounds(235, 525, 500, 50); 
        DelButton.setBounds(260, 720, 450, 50); 
        Bbutton.setBounds(235, 590, 500, 50);        
        
        f.add(Cbutton);
        f.add(Bbutton);
        f.add(logButton);
        f.add(DelButton);
        
        //set font
        userName.setFont(newFont);
        userEmail.setFont(newFont);
        userPassword.setFont(newFont);
        Cbutton.setFont(newFont2);
        Bbutton.setFont(newFont2);
        logButton.setFont(newFont2);
        DelButton.setFont(newFont2);

        //Feedback after the user change information
        Cbutton.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Your information has been successfully updated.", "Profile update", JOptionPane.PLAIN_MESSAGE);
            }

    
         });
        
         //Delete the account 
        DelButton.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this account?","Quesiton", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            // Check if the user clicked "Yes"
                if (response == JOptionPane.YES_OPTION) {
                    // If Yes show the main page
                     MainFrame mainFrame = new MainFrame();

                    //close the current frame
                    ((JFrame) SwingUtilities.getWindowAncestor(logButton)).dispose();
                }
            }

    
         });
        
        //log out
        logButton.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                // Check if the user clicked "Yes"
                if (response == JOptionPane.YES_OPTION) {
                    // If Yes show the main page
                     MainFrame mainFrame = new MainFrame();

                    //close the current frame
                    ((JFrame) SwingUtilities.getWindowAncestor(logButton)).dispose();
                }
            }
        });
//        //back to home page
//        Bbutton.addActionListener(new ActionListener() {   
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dHomePage dp = new dHomePage(user);
//                dp.showPage();
//            }
//
//    
//         });

 
}
}