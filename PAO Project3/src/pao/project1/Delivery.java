/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Arthur
 */
public class Delivery {

    Scanner input = new Scanner(System.in);
    private String name;
    private String contact;
    private Order currentOrder;
    private String username;
    private String password;

    //Order noOrder = new Order("Empty Order Status");
    //Delivery(String notPickedUp)
    // {
    //    name=notPickedUp;
    // }
    Delivery(String newName, String newUsername, String newPassword, String newContact) {
        name = newName;
        contact = newContact;
        username = newUsername;
        password = newPassword;
        //   currentOrder = noOrder;
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

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void acceptDelivery(Order order) {
        try {
            Connection con = getConnection();
            PreparedStatement delStatusUpdate = con.prepareStatement("UPDATE deliverymen SET delCurrentOrder = true WHERE username = '" + username + "'");
            delStatusUpdate.executeUpdate();
            order.updateStatus("Being delivered");
            order.setDelivery(this);
            currentOrder = order;
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void setAsDelivered() {
        try {Connection con = getConnection();
            PreparedStatement delStatusUpdate = con.prepareStatement("UPDATE deliverymen SET delCurrentOrder = false WHERE username = '" + username + "'");
            delStatusUpdate.executeUpdate();
            currentOrder.updateStatus("Delivered!");
            currentOrder = null;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Dispute openDispute() {
        System.out.println("Enter the problem that you are facing");
        String problem = input.nextLine();
        Dispute dispute = new Dispute("Unsolved", problem, currentOrder, "Delivery");
        return dispute;

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
