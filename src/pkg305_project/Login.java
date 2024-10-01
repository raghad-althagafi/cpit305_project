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
public class Login {
    
    public static final Font fontText = new Font("Serif",Font.BOLD, 25);
    public static final Font fontTitle = new Font("Serif",Font.BOLD, 40);

    public Login() {
        //create frame
        JFrame frame = new Frame("Login");
        
        
        //create panel
        JPanel panel = new JPanel();
        //set background color
        panel.setBackground(ColorsFonts.lightPurple);
        //add panel to frame
        frame.add(panel);
        //set panel layout to null
        panel.setLayout(null);
        
        
        //Login label
        JLabel Login = new JLabel("Login");
        Login.setBounds(370, 115, 200, 80);
        //set color of label
        Login.setForeground(ColorsFonts.darkPurple);
        //set font of label
        Login.setFont(ColorsFonts.fontTitle);
        //add it to the panel
        panel.add(Login);
        
        
        //username label
        JLabel username= new JLabel("Username");
        username.setBounds(240, 330, 150, 30);
        //set color and font of label
        username = label(username);
        //add it to the panel
        panel.add(username);
        
        
        //username textField
        JTextField userText = new JTextField();
        userText.setBounds(400, 330, 250, 40);
        //add textField to the panel
        panel.add(userText);
        
        
        //pass label
        JLabel pass= new JLabel("Password");
        //set color and font of label
        pass = label(pass);
        pass.setBounds(240, 430, 150, 30);
        //add label to the panel
        panel.add(pass);
        
        
        //pass passField
        JPasswordField passText = new JPasswordField();
        passText.setBounds(400,430 , 250, 40);
        //add passField to the panel
        panel.add(passText);
        
        
        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 550, 400, 40);
        //set color and font button
        loginButton = button(loginButton);
        //add passField to the panel
        panel.add(loginButton);
        
        
        //register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(250, 650, 400, 40);
        //set color and font button
        registerButton = button(registerButton);
        //add button to the panel
        panel.add(registerButton);
        
        
        //exit button
        JButton exit = new JButton("Exit");
        exit.setBounds(40, 30, 100, 40);
        //set color and font button
        exit = button(exit);
        panel.add(exit);
        
        
        //clicking login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open register frame
                StudentInterface s = new StudentInterface();
                User user = new User("Leena", 0, "r20zxc");
                dHomePage dp = new dHomePage(user);
                dp.showPage();
            }
        });
        
        //clicking register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open register frame
                Register r = new Register();
            }
        });
        
        
        //clicking exit button
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        
        }
    
    //method to set color and font of buttons
    public JButton button(JButton button){
        //set font of  button
        button.setFont(ColorsFonts.fontText);
        //set color of button text
        button.setForeground(Color.WHITE);
        //set background color of button
        button.setBackground(ColorsFonts.darkPurple);
        //return button
        return button;
        }
    
    
    //method to set color and font of label
    public JLabel label(JLabel label){
        //set font of label
        label.setFont(ColorsFonts.fontText);
        //set color of label
        label.setForeground(ColorsFonts.darkPurple); 
        return label;
    }
    
    }
    

