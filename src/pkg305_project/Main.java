/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import javax.swing.JFrame;

/**
 *
 * @author AHC
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new MainFrame(); //create main frame

        createDB(); //create database
    }

    public static void createDB() {

        Database db = new Database(); //database 

    }

}
