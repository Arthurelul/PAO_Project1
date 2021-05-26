/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Restaurant {

    Scanner input = new Scanner(System.in);
    private String name;
    private String itemName;
    private int idCounter = 0;
    private String location;
    private String contact;
    private String username;
    private String password;


    Restaurant(String newName, String newUsername, String newPassword, String newLocation, String newContact) {
        name = newName;
        location = newLocation;
        contact = newContact;
        username = newUsername;
        password = newPassword;
    }

    public boolean checkUsername(String userCheck) {
        if (userCheck.equals(username)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkPassword(String passCheck) {
        if (passCheck.equals(password)) {
            return true;
        } else {
            return false;
        }

    }

    public void showItems() {
        try {
            int index=1;
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT itemName FROM menu WHERE resUsername ='" + username + "'");
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                System.out.println(index + ". " + result.getString(1));
                index += 1;
            }

        } catch (Exception e) {

        }

    }

   

    public void addItem() {
        try {
            Connection con = getConnection();
            System.out.println("Enter the name of the item");
            itemName = input.next();
            System.out.println("Enter the price of the item");
            Double price = input.nextDouble();
            PreparedStatement posting = con.prepareStatement("INSERT INTO menu (itemName, price,resUsername) VALUES ('" + itemName + "','" + price + "','" + username + "')");
            posting.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeItem() {
        try {

            Connection con = getConnection();
            System.out.println("Enter the name of the item you want to remove");
            showItems();
            itemName = input.next();
            Statement stmt = con.createStatement();
            String sql = "DELETE FROM menu WHERE itemName = '" + itemName + "' AND resUsername = '" + username + "'";
            stmt.executeUpdate(sql);
            //PreparedStatement deleting = con.prepareStatement("DELETE FROM menu WHERE itemName = '" + itemName + "' AND resUsername = '" + username + "'");
            //deleting.executeUpdate();
            System.out.println(itemName + " deleted from the menu");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    
    boolean isOpen;

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String newContact) {
        contact = newContact;
    }

   

    public boolean checkOpen() {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT isOpen FROM restaurants WHERE resUsername ='" + username + "'");
            ResultSet result = statement.executeQuery();
            result.next();

            return result.getBoolean(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void setOpen() {
        try {
            Connection con = getConnection();
            System.out.println("Enter 0 for open or 1 for closed");
            boolean open = input.nextInt() == 0;
            if (open) {
                PreparedStatement resOpenStatus = con.prepareStatement("UPDATE restaurants SET isOpen = true WHERE username = '" + username + "'");
                resOpenStatus.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static Connection getConnection() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/paodb", "root", "root");
            // System.out.println("Connected to database");
            return conn;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
