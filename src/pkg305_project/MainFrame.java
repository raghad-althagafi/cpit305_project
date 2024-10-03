/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author msbbr
 */
public class MainFrame {
    
       
    public MainFrame() {
        
        //create frame
        JFrame frame = new Frame("Main");
        //create panel
        JPanel panel = new JPanel();
        //set the background color
        panel.setBackground(ColorsFonts.lightPurple);
        //add panel to frame
        frame.add(panel);
        //set layout
        panel.setLayout(null);
        
        
        //logo of system
        ImageIcon imageIcon = new ImageIcon("logo2.jpeg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(390, 170, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        panel.add(imageLabel, BorderLayout.CENTER);
        
        
        //hello label
        JLabel helloLabel = new JLabel("KAU Events");
        //set color of label
        helloLabel.setForeground(ColorsFonts.darkPurple); 
        //set font of label
        helloLabel.setFont(ColorsFonts.fontTitle);
        helloLabel.setBounds(360, 300, 600, 200);
        panel.add(helloLabel);
        
        
        //start button
        JButton start = new JButton("Start now");
        //set color and font button
        start = button(start);
        start.setBounds(290, 590, 350, 40);
        panel.add(start);
        
        
        //exit button
        JButton exit = new JButton("Exit");
        exit.setBounds(290, 650, 350, 40);
        //set color and font button
        exit = button(exit);
        panel.add(exit);
        
        
        //clicking start button
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open login frame
                Login l = new Login();
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
        button.setForeground(Color.white);
        //set background color of button
        button.setBackground(ColorsFonts.darkPurple);
        //return button
        return button;
        }
    
    
}
