/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import javax.swing.JFrame;

/**
 *
 * @author msbbr
 */
public class Frame extends JFrame{

    public Frame(String title) {
        super(title);
        this.setSize(1000, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
