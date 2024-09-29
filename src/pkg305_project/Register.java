/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author msbbr
 */
public class Register {
    public static final Font fontText = new Font("Serif",Font.BOLD, 25);
    public static final Font fontTitle = new Font("Serif",Font.BOLD, 40);
    public static final Color Darkgreen = new Color(5,125,114);

    public Register() {
        
        //create frame
        JFrame main = new Frame("Register");
        //create panel
        JPanel panel = new JPanel();
        //set background colot to gray
        panel.setBackground(Color.DARK_GRAY);
        //add panel to frame
        main.add(panel);
        //set layout to null
        panel.setLayout(null);
        
        
        //Register label
        JLabel register = new JLabel("Register");
        //set font of Register label
        register.setFont(fontTitle);
        //set color of Register label
        register.setForeground(Color.WHITE); 
        register.setBounds(400, 90, 200, 80);
        //add label to panel
        panel.add(register);
        
        
        //username label
        JLabel username= new JLabel("Username");
        username.setBounds(200, 250, 150, 30);
        //set color and font of label
        username = label(username);
        //add username label to panel
        panel.add(username);
        
        
        //username textField
        JTextField userText = new JTextField(8);
        userText.setBounds(420, 250, 350, 40);
        //add textfield to panel
        panel.add(userText);
        
        
        //email label
        JLabel email= new JLabel("Email");
        //set color and font of label
        email = label(email);
        email.setBounds(200, 350, 150, 30);
        //add email to panel
        panel.add(email);
        
        
        //email textField
        JTextField emailText = new JTextField();
        emailText.setBounds(420, 350, 350, 40);
        //add textField to panel
        panel.add(emailText);
        
        //pass label
        JLabel pass= new JLabel("Password");
        //set color and font of label
        pass = label(pass);
        pass.setBounds(200, 450, 150, 30);
        //add pass label to panel
        panel.add(pass);
        
        
        //pass TextField
        JPasswordField passText = new JPasswordField();
        passText.setBounds(420, 450, 350, 40);
        //add pass TextField to panel
        panel.add(passText);
        
        
        //Confirm password label
        JLabel confPass= new JLabel("Confirm Password");
        //set color and font of label
        confPass = label(confPass);
        confPass.setBounds(200, 550, 350, 30);
        //add Confirm pass TextField to panel
        panel.add(confPass);
        
        
        //confirm passField
        JPasswordField cofText = new JPasswordField();
        cofText.setBounds(420, 550, 350, 40);
        //add confirm passField to panel
        panel.add(cofText);
        
       
        //Register button
        JButton registerButton = new JButton("Register");
        //set color and font button
        registerButton = button(registerButton);
        registerButton.setBounds(340, 670, 350, 40);
        //add Register button to panel
        panel.add(registerButton);
        
        
        //login button
        JButton login = new JButton("Back to Login");
        //set color and font button
        login = button(login);
        login.setBounds(340, 740, 350, 40);
        //add login button to panel
        panel.add(login);
        
        
        //clicking Login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login r = new Login();
            }
        });
        
    
        
    }
    //method to set color and font of buttons
    public JButton button(JButton button){
        //set font of  button
        button.setFont(fontText);
        //set color of button text
        button.setForeground(Color.WHITE);
        //set background color of button
        button.setBackground(Darkgreen);
        //return button
        return button;
        }
    
    
    //method to set color and font of label
    public JLabel label(JLabel label){
        //set font of label
        label.setFont(fontText);
        //set color of label
        label.setForeground(Color.WHITE); 
        return label;
    }
    
       
       
}
