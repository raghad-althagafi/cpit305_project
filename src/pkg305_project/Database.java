/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg305_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author msbbr
 */
public class Database {
    
    private Connection con;

    public Database() {
        //(1) create the database
        createDB();
        //(2) connect to database
        connectToDatabase();
        //(3) create DB tables 
        createTables();
        
    }
    
    //------------------------------------
    
    //method for creating DB
    public void createDB(){
    
    //statment for create DB
    String createDatabase = "CREATE DATABASE IF NOT EXISTS KAUEvents";
    
    //connect to mySql server
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "Leena1234");
             Statement st = conn.createStatement()) {
            // Execute statement to create the database
            st.executeUpdate(createDatabase);
            System.out.println("Database created if not exists.");
        } catch (SQLException s) {
            System.out.println("SQL statement for database creation is not executed!");
        }
        
    }
    
    //------------------------------------
    
    // Connect to the database
    private void connectToDatabase() {
        try {
            //Set the connection to KAUEVENTS DB
            String connectionURL = "jdbc:mysql://localhost:3306/KAUEvents";
            con = DriverManager.getConnection(connectionURL, "root", "Leena1234");
            System.out.println("Connected to the database.");
        } catch (SQLException s) {
            System.out.println("SQL statement for connecting to the database is not executed!");
        }
    }
    
    //------------------------------------
    //method for creating tables of DB
    public void createTables(){
        
        //user table statment
        String usersTable = "CREATE TABLE IF NOT EXISTS users ("
                                + "username VARCHAR(13) NOT NULL PRIMARY KEY, "
                                + "email VARCHAR(50) NOT NULL, "
                                + "role VARCHAR(50),"
                                + "password VARCHAR(50) NOT NULL"
                                +")";
        
        try(Statement st = con.createStatement()){
            //create users table
            st.executeUpdate(usersTable);
            System.out.println("user table created");
        }
        catch (SQLException s){
         System.out.println("SQL statement is not executed!");
        }
    }
    

    
    //method to insert useres in users table
    public void registerUser(String username, String email, String password){
        
        //insert user row
        String user = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        
        try (PreparedStatement st = con.prepareStatement(user)){
            
            //set the values of row
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, password);
            
            //excute statment
            st.executeUpdate();
            System.out.println("user added!");
        }
        catch (SQLException s){
         System.out.println("SQL statement is not executed!");
        }
    }
    
    //method to check user Login
    public boolean checkLogin(String username,String Password){
        String query = "SELECT password FROM users WHERE username=?";
        
        try (PreparedStatement st = con.prepareStatement(query)){
            
            //set the values of username
            st.setString(1, username);
            
            //excute statment
            ResultSet rs = st.executeQuery();
            
            //if user exists
            if(rs.next()){
                //the real pass
                String RealPass = rs.getString("password");
                //check if the entered pass equla the real pass
                boolean flag = RealPass.trim().equalsIgnoreCase(Password.trim());
                //return the value
                return flag;
            }
            
            //if user not found
            else{
                System.out.println("User not found");
                return false;
            }
        }
        catch (SQLException s){
         System.out.println("SQL statement is not executed!");
        }
        
        return false;
    }
    
//    public boolean checkRole(){
//        
//    }
    
    
     
    // Method to print all users
    public void printAllUsers() {
        String query = "SELECT * FROM users"; // Use the actual table name

        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                // Assuming the users table has columns: username, email (change as necessary)
                String username = rs.getString("username");
                String pass = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");

                // Print the row
                System.out.println("Username: "+username+" Email: "+email+ ", pass "+pass +" ,  role "+ role);
            }
        } catch (SQLException s) {
            System.out.println("SQL statement for retrieving users is not executed!");
            s.printStackTrace();
        }
    }
}
