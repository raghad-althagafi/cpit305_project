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


/**
 *
 * @author AHC
 */
public class dHomePage {
    private User user;
    
    
    public dHomePage(User user){
        this.user = user;
   
    }
    
    
    public void showPage() {
        
        String name = user.getName();
        String EventName = "Event Name";
        String EventStart = "Day of start";
        
        //lables
        JLabel events = new JLabel("KAU EVENTS");
        //set font,color and its position
        events.setFont(new Font("Comic Sans", Font.BOLD, 25));
        events.setForeground(Color.WHITE);
        events.setBounds(250, 20, 200, 100);
        
        //create the events

        JPanel E1 = createStyledPanel(name, EventName, EventStart, 150, true);// If true, it means that the user of this account is the one who wrote the event information

        // JPanel line = lines(302);
        JPanel E2 = createStyledPanel("shahad", EventName, EventStart, 305, false);
        //JPanel line2 = lines(457);

        JPanel E3 = createStyledPanel("shahad", EventName, EventStart, 460, false);
        //JPanel line3 = lines(612);

        JPanel E4 = createStyledPanel(name, EventName, EventStart, 615, true);
        //JPanel line4 = lines(765);
        
        
        //create the left panel
        JPanel panel = new JPanel();
        panel.setBackground(new Color(8, 78, 65));
        panel.setBounds(0, 0, 300, 900);
        panel.setLayout(null);
        
        //create the right panel
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.darkGray);
        panel2.setBounds(300, 0, 700, 900);
        panel2.setLayout(null);
        
        //add the components to the right panel
        JButton addEventButton = createStyledButton("Add new Event");
        addEventButton.setBounds(90, 100, 500, 50);
        
        panel2.add(addEventButton);
        panel2.add(events);
        panel2.add(E1);

        //  panel2.add(line);
        panel2.add(E2);
        // panel2.add(line2);
        panel2.add(E3);
        //panel2.add(line3);
        panel2.add(E4);
        // panel2.add(line4);
        

        
        //add the components to the left panel
        JButton userButton = createStyledButton(name);


        userButton.setBounds(20, 30, 200, 50);
        panel.add(userButton);
        
        
        //Details details = new Details();
        
        //create the frame of the home page
        Frame Doctor = new Frame("Home page");

        Doctor.setLayout(null);
        Doctor.add(panel);
        Doctor.add(panel2);

        
        //If the addEvent button is clicked
        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //شاشه اضافه الفعاليه هنا
            }
        });
        
        //If the user button is clicked
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               infoPage iPage = new infoPage(user);
               iPage.showPage();
               
            }
        });
       
        
        
        
     
    }

    
//create button and set its attribute
    public static JButton createStyledButton(String text) {
        
        JButton button = new JButton(text);
        button.setBackground(new Color(8, 78, 65));
        button.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        return button;
    }
    
    

    public  JPanel createStyledPanel(String name, String EventName, String EventStart, int y, boolean isCreator) {
        //buttons
        JButton Ebutton1 = createStyledButton("\uD83D\uDC64 " + name);
        Ebutton1.setBounds(10, 10, 150, 50);
        Ebutton1.setEnabled(false);

        JButton view1 = createStyledButton("view details");
        view1.setBounds(530, 90, 140, 40);

        JLabel el1 = new JLabel();
        el1.setText(EventName);
        el1.setFont(new Font("Comic Sans", Font.BOLD, 20));
        el1.setForeground(Color.WHITE);
        el1.setVisible(true);
        el1.setBounds(180, 8, 700, 50);

        JLabel details1 = new JLabel();
        details1.setText(EventStart);
        details1.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        details1.setForeground(Color.WHITE);
        details1.setVisible(true);
        details1.setBounds(180, 55, 700, 50);

        JPanel E1 = new JPanel();
        E1.setBackground(Color.darkGray);
        E1.setBounds(0, y, 700, 150);
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
               //اذا انحذفت الفعاليه
               
               
            }
        });
            

        }

        return E1;

    }
 
    
    
    
} 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  