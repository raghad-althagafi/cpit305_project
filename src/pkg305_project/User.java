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

    public User(String Username,Database db) {
        this.Username = Username;
        this.db = db;
        String[] emailAndPassword = db.findUserInformation(Username);
         this.email = emailAndPassword[0];
        this.password = emailAndPassword[1];

    }

    public String getUsername() {
        return Username;
    }



    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }
   
   
   
   
}
