/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class User {

    Scanner input = new Scanner(System.in);
    private String name;
    private String contact;
    private Order order;
    private String username;
    private String password;
    
    List<Item> items = new ArrayList<Item>();

    User(String newName, String newUsername, String newPassword, String newContact) {
        
        contact = newContact;
        name = newName;
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

    public Order placeOrder() {
        try {

            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT resNmae, resLocation FROM restaurants ORDER BY resNmae");
            ResultSet result = statement.executeQuery();
            String resUsername;
            int index = 0;
            System.out.println("Chose a restaurant:");
            while (result.next()) {
                index += 1;
                System.out.println(index + ". " + result.getString(1) + "     Location: " + result.getString(2));
            }
            index = input.nextInt();
            statement = con.prepareStatement("SELECT username,NTH_VALUE(resNmae, " + index + ") OVER  ( ORDER BY resNmae ASC) FROM restaurants where resNmae IS NOT NULL");
            result = statement.executeQuery();
            result.next();
            while (result.getString(2) == null) {
                result.next();

            }
            //System.out.println(result.getString(1));
            resUsername = result.getString(1);

            System.out.println("Enter the name of the items you want to be added to your order followed by 0");
            statement = con.prepareStatement("SELECT itemName,price FROM menu WHERE resUsername ='" + resUsername + "'");
            result = statement.executeQuery();
            index=1;
            while (result.next()) {
                System.out.println(index + ". " + result.getString(1)+"    Price: "+result.getString(2));
                index += 1;
            }
            String itemName = ".";

            itemName = input.next();
            while (!itemName.equals("0")) {

                statement = con.prepareStatement("SELECT itemName,price,resUsername FROM menu WHERE resUsername = '" + resUsername + "' AND itemName = '" + itemName + "'");
                result = statement.executeQuery();
                result.next();
                itemName = input.next();
            }
            items.add(new Item(result.getString(1), result.getDouble(2)));
            statement = con.prepareStatement("SELECT username, resNmae,resPass,resLocation,resContact FROM restaurants WHERE username = '" + resUsername + "'");
            result = statement.executeQuery();
            result.next();

            order = new Order(this, items, new Restaurant(result.getString(2),result.getString(1),result.getString(3),result.getString(4),result.getString(5)));
            System.out.println("Order placed succesfully! Price : " + order.calculatePrice());
            return order;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public void cancelOrder() {
        order.updateStatus("Canceled");
    }

    public boolean orderOpened() {
        return order != null;
    }

    public Dispute openDispute() {
        System.out.println("Enter the problem you are facing");
        String problem = input.nextLine();
        Dispute dispute = new Dispute("Unsolved", problem, order, "User");
        return dispute;

    }

    public void payOrder() {
        order.paidOrder();

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
