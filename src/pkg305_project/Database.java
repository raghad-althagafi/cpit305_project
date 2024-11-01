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
        //create event table 
        createTablesevent();
    }
    
    //------------------------------------
    
    //method for creating DB
    public void createDB(){
    
    //statment for create DB
    String createDatabase = "CREATE DATABASE IF NOT EXISTS KAUEvents";
    
    //connect to mySql server
    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "raghad");
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
            //Set the connection to KAU_EVENTS DB
            String connectionURL = "jdbc:mysql://localhost:3306/KAUEvents";
            con = DriverManager.getConnection(connectionURL, "root", "raghad");
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
        String user = "INSERT INTO users (username, email, role, password) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement st = con.prepareStatement(user)){
            
            //set the values of row
            st.setString(1, username);
            st.setString(2, email);
            if(email.toLowerCase().contains("stu")){
                st.setString(3, "Student");
            }
            else{
                st.setString(3, "Doctor");
            }
            st.setString(4, password);
            
            //excute statment
            st.executeUpdate();
            System.out.println("user added!");
        }
        catch (SQLException s){
         System.out.println("SQL statement register is not executed!");
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
    
        public boolean checkRole(String username){
            
            String query = "SELECT role FROM users WHERE username=?";
            
            try(PreparedStatement st = con.prepareStatement(query)){
                //set the values of username
                st.setString(1, username);
                //excute statment
                ResultSet rs = st.executeQuery();
                
                //if user exists
                if(rs.next()){
                //retrive role of user
                String role = rs.getString("role");
                
                //check role
                if(role.equalsIgnoreCase("Student")){
                    return true;
                }
                else{ //if Dr
                    return false;
                }
                
            }
            }
            catch (SQLException s){
            System.out.println("SQL statement is not executed!");
            }
            return false;
        }
    
    
     
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
        //------------------------------------
    // Method to create tables in the database
    public void createTablesevent() {
        // SQL statement to create a table for events
        String createEventsTable = "CREATE TABLE IF NOT EXISTS events ("
                + "eventID INT AUTO_INCREMENT PRIMARY KEY, "
                + "eventName VARCHAR(255), "
                + "eventDate VARCHAR(255), "
                + "eventTime VARCHAR(255), "
                + "location VARCHAR(255), "
                + "college VARCHAR(255), "
                + "details TEXT"
                + ")";
        
        try (Statement st = con.createStatement()) {
            st.executeUpdate(createEventsTable);
            System.out.println("Events table created if not exists.");
        } catch (SQLException s) {
            System.out.println("SQL statement for table creation is not executed!");
        }
    }
    
    // Method to add event to the database
    public void addEvent(String eventName, String date, String time, String location, String college, String details) {
        String insertEventSQL = "INSERT INTO events (eventName, eventDate, eventTime, location, college, details) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = con.prepareStatement(insertEventSQL)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, date);
            pstmt.setString(3, time);
            pstmt.setString(4, location);
            pstmt.setString(5, college);
            pstmt.setString(6, details);

            pstmt.executeUpdate();
            System.out.println("Event added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to add event: " + e.getMessage());
        }
    }
    
    
    //method to find user's email and password
    public String[] findUserInformation(String username) {
        String email = null, password = null, userName = null;
        try {
            String query = "SELECT email, password, username FROM users WHERE username = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, username); 

            ResultSet result = st.executeQuery();
            if (result.next()) {
                email = result.getString("email");
                password = result.getString("password");
                userName = result.getString("username");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[] { email, password ,userName};
    }
    
    //method to update user's information
    public boolean UpdateUser(String oldUsername, String newUsername, String email, String password) {

        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE username = ?";

        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, newUsername); // Set the new username
            st.setString(2, email);      // Set the new email
            st.setString(3, password);   // Set the new password
            st.setString(4, oldUsername); // Set the old username (condition)

            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                //if user information successfully apdated
                return true;
            } else {
                //failed to update information
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Failed to update user: " + e.getMessage());
        }
        return false;
    }
    
     public boolean deleteUser(String username) {
        String query = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, username);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                //if user successfully deleted
                return true;
            } else {
                //failed to delete user
                return false;
            }

        } catch (SQLException s) {
            System.out.println("SQL statement for user deletion is not executed!");
            s.printStackTrace();
            return false;
        }
    }
    
}

