/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

/**
 *
 * @author AHC
 */
public class User {
   private String Username;
   private Database db;
   private String password;
   private String email;

    public User(String Username) {
        this.Username = Username;
        db = new Database();
        String[] emailAndPassword = db.findUserInformation(Username);
         this.email = emailAndPassword[0];
        this.password = emailAndPassword[1];

    }

    public void setEverything(String Username, String email,String password){
        this.Username = Username;
        this.email = email;
        this.password = password;
    }
    
    public String getUsername() {
        return db.findUserInformation(Username)[2];
    }



    public String getPassword() {
        return db.findUserInformation(Username)[1];
    }

    public String getEmail() {
        return db.findUserInformation(Username)[0];
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }


    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }
   
    public boolean Role(){
        return db.checkRole(Username);
    }
   
   
}
