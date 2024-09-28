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
   private String name;
   //private String faculty;
   private long password;
   private String email;

    public User(String name, long password, String email) {
        this.name = name;
        //this.faculty = faculty;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

//    public String getFaculty() {
//        return faculty;
//    }

    public long getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setFaculty(String faculty) {
//        this.faculty = faculty;
//    }

    public void setPassword(long password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
   
   
}
