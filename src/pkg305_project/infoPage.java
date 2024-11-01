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
        f.getContentPane().setBackground(ColorsFonts.lightPurple);
        f.setLayout(null);
        
        

        //labels
        JLabel userName = labelStyle("Username"); 
        f.add(userName);
        JLabel userEmail = labelStyle("Email");             
        f.add(userEmail);
        JLabel userPassword =labelStyle("Password");              
        f.add(userPassword);
        
        //label's positions
        userName.setBounds(235, 5, 500, 500);
        userEmail.setBounds(235, 60, 500, 500);
        userPassword.setBounds(235, 120, 500, 500);

        //textFields
        JTextField nameTF = new JTextField(user.getUsername());
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
        JButton Cbutton = buttonStyle("Confirm");
        JButton Bbutton = buttonStyle("Back");
        JButton logButton = buttonStyle("Log out");
        JButton DelButton = new JButton("Delete the account");
        DelButton.setBackground(Color.RED);
        DelButton.setForeground(Color.WHITE);
        DelButton.setFont(ColorsFonts.fontButton);

        //button's positions
        Cbutton.setBounds(235, 460, 500, 50); 
        logButton.setBounds(235, 525, 500, 50); 
        DelButton.setBounds(260, 720, 450, 50); 
        Bbutton.setBounds(235, 590, 500, 50);        
        
        f.add(Cbutton);
        f.add(Bbutton);
        f.add(logButton);
        f.add(DelButton);
        

        

        //Feedback after the user change information
        Cbutton.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean updated = user.getDb().UpdateUser(user.getUsername(), nameTF.getText(), EmailTF.getText(), PasswordTF.getText());
                //Checks if it is updated or not
                if(updated){
                JOptionPane.showMessageDialog(f, "Your information has been successfully updated.", "Profile update", JOptionPane.PLAIN_MESSAGE);
                }
                else{
                 JOptionPane.showMessageDialog(f, "Your information did not updated.", "Profile update", JOptionPane.PLAIN_MESSAGE);   
                }
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
                    ((JFrame) SwingUtilities.getWindowAncestor(DelButton)).dispose();
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
        //back to home page
        Bbutton.addActionListener(new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent e) {
                 //close the current frame
                    ((JFrame) SwingUtilities.getWindowAncestor(Bbutton)).dispose();
            }

    
         });

 
}
  
    
//mathod to set font and color for buttons
    public JButton buttonStyle(String s) {
        JButton b = new JButton(s);
        b.setBackground(ColorsFonts.darkPurple);
        b.setForeground(Color.WHITE);
        b.setFont(ColorsFonts.fontButton);

        return b;
    }


//mathod to set font and color for labels
    public JLabel labelStyle(String s){
       JLabel l = new JLabel(s);
        l.setForeground(ColorsFonts.darkPurple);
        l.setFont(ColorsFonts.fontText);
       
        return l;
    }
}