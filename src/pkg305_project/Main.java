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
        //create main frame
        new MainFrame();
        //create login frame
        //new Login();
        //create new register frame
       // new Register();
        // create doctor home page
//        User user = new User("Raghad", 0, "r20zxc");
//        dHomePage dp = new dHomePage(user);
//        dp.showPage();
        //create information page
//        infoPage iPage = new infoPage(user);
//        iPage.showPage();
        //Add event page
//         ADDevent frameCreator = new ADDevent();
//        JFrame frame = frameCreator.createFrame("Add Event");
//        frame.setVisible(true);
//        new Details();
//        StudentInterface st = new StudentInterface(user);
        
        
        //database 
        createDB();
    }
    
    public static void createDB(){
        //database 
        Database db = new Database();
        
        //db.printAllUsers();
        
        //System.out.println(db.findUser("raghad"));
//        boolean t = db.UpdateUser("raghad", "rraghad", "rraghad", "rraghad");
//        System.out.println(t);
    }

}
