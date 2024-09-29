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

/**
 *
 * @author msbbr
 */
public class MainFrame {
    
       public static final Font fontText = new Font("Serif",Font.BOLD, 25);
       public static final Font fontTitle = new Font("Serif",Font.BOLD, 40);
       public static final Color Darkgreen = new Color(5,125,114);

    public MainFrame() {
        
        //create frame
        JFrame main = new Frame("Main");
        //create panel
        JPanel panel = new JPanel();
        //set the background color
        panel.setBackground(Color.DARK_GRAY);
        //add panel to frame
        main.add(panel);
        //set layout
        panel.setLayout(null);
        
        
        //hello label
        JLabel helloLabel = new JLabel("KAU Events");
        //set color of label
        helloLabel.setForeground(Color.WHITE); 
        //set font of label
        helloLabel.setFont(fontTitle);
        helloLabel.setBounds(360, 350, 600, 200);
        panel.add(helloLabel);
        
        
        //start button
        JButton start = new JButton("Start now");
        //set font of button 
        start.setFont(fontText);
        start.setBounds(290, 590, 350, 40);
        //set background color of button
        start.setBackground(Darkgreen);
        //set the text color
        start.setForeground(Color.WHITE);
        panel.add(start);
        
        
        //clicking start button
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open login frame
                Login l = new Login();
            }
        });
        
        
    }
    
    
}
