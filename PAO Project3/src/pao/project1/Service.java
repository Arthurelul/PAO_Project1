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
public class Service {

    Scanner input = new Scanner(System.in);
    int resNumber = 0;
    
    public List<Order> orders = new ArrayList<Order>();
    public List<Dispute> disputes = new ArrayList<Dispute>();
   

    Service() {
        try {
            Connection con = getConnection();
            Scanner input = new Scanner(System.in);
            Statement stmt = con.createStatement();

            int universalCase = 1;
            int individualCase = 1;
            int account;
            boolean ok = true;
            
            boolean loggedIn = false;
            boolean accountFound = false;
            while (universalCase != 0) {
                System.out.println("Hello! Are you: 1. a User, 2. a Restaurant, 3. a Delivery man or 4. a Dispatcher? 0->Exit");
                universalCase = input.nextInt();

                switch (universalCase) {

                    case 1: //User Case
                    {
                        User user = null;
                        loggedIn = false;
                        System.out.println("Are you a new user or an existing one?");
                        System.out.println("1. I am a new user. Create an account.");
                        System.out.println("2. I am an existing user. Log in.");
                        account = input.nextInt();
                        if (account == 2) {
                            user = userLogin();
                            if (user == null) {
                                break;
                            }

                        }

                        if (account == 1) {
                            user = userCreateAccount();

                        }

                        individualCase = 1;
                        //System.out.println("Enter Your Name and phone number");
                        //User user = new User(input.next(), input.next(), restaurants);
                        while (individualCase != 0) {
                            System.out.println("1. Place Order");
                            System.out.println("2. Cancel Order");
                            System.out.println("3. Open Dispute");
                            System.out.println("0. Exit");
                            individualCase = input.nextInt();
                            switch (individualCase) {
                                case 1 ->  {
                                    orders.add(user.placeOrder());

                                }

                                case 2 ->  {
                                    user.cancelOrder();
                                }
                                case 3 ->  {
                                    if (user.orderOpened()) {
                                        disputes.add(user.openDispute());
                                    } else {
                                        System.out.println("There are no open orders. You can't open a dispute.");
                                    }
                                }

                            }
                        }
                        break;
                    }

                    case 2: //Restaurant Case
                    {
                        Restaurant res = null;
                        
                        System.out.println("Are you a new restaurant or an existing one?");
                        System.out.println("1. I am a new restaurant. Create an account.");
                        System.out.println("2. I am an existing restaurant. Log in.");
                        account = input.nextInt();
                        if (account == 2) {
                            res = restaurantLogin();
                            if (res == null) {
                                break;
                            }

                        }

                        if (account == 1) {
                            res = restaurantSignUp();
                        }

                        individualCase = 1;
                        while (individualCase != 0) {
                            System.out.println("1. Set Open");
                            System.out.println("2. Add an item to the menu");
                            System.out.println("3. Remove an item from the menu");
                            System.out.println("0. Exit");
                            individualCase = input.nextInt();
                            switch (individualCase) {
                                case 1 ->  {
                                    res.setOpen();
                                    break;

                                }
                                case 2 ->  {
                                   
                                    res.addItem();
                                    break;
                                }
                                case 3 ->  {
                                    
                                    res.removeItem();
                                    break;
                                }

                            }
                        }
                        break;
                    }

                    case 3: //Delivery case
                    {
                        Delivery delivery = null;
                        loggedIn = false;
                        System.out.println("Are you a new delivery man or an existing one?");
                        System.out.println("1. I am a new delivery man. Create an account.");
                        System.out.println("2. I am an existing delivery man. Log in.");
                        account = input.nextInt();
                        if (account == 2) {
                            delivery = deliveryLogin();
                            if (delivery == null) {
                                break;
                            }

                        }

                        if (account == 1) {
                            delivery = deliverySignUp();
                        }

                        individualCase = 1;

                        while (individualCase != 0) {
                            System.out.println("1. Pick up an order");
                            System.out.println("2. Finish a delivery");
                            System.out.println("3. Open a dispute");
                            System.out.println("0. Exit");
                            individualCase = input.nextInt();
                            switch (individualCase) {
                                case 1: {
                                    if (delivery.getCurrentOrder() != null) {
                                        System.out.println("You already have an ongoing order!");
                                        break;
                                    }
                                    boolean openOrders = false;
                                    for (int i = 0; i < orders.size(); i++) {
                                        if (orders.get(i).getStatus().equals( "Open")) {
                                            openOrders = true;
                                        }
                                        break;
                                    }
                                    if (openOrders) {
                                        System.out.println("Chose an order:");
                                        for (int i = 0; i < orders.size(); i++) {
                                            if (orders.get(i).getStatus().equals( "Open")) {
                                                System.out.print(i + ". ");
                                                orders.get(i).showOrder();
                                            }
                                        }
                                        delivery.acceptDelivery(orders.get(input.nextInt()));

                                    } else {
                                        System.out.println("There are no open orders!");
                                    }
                                    break;

                                }
                                case 2: {
                                    if (delivery.getCurrentOrder() == null) {
                                        System.out.println("You have no ongoing orders!");
                                        break;
                                    }
                                    delivery.setAsDelivered();
                                    System.out.println("Thank you for the delivery!");
                                    break;

                                }
                                case 3: {
                                    if (delivery.getCurrentOrder() == null) {
                                        System.out.println("You can't open a dispute if you have no ongoing order!");
                                    } else {

                                        disputes.add(delivery.openDispute());
                                        break;
                                    }
                                }

                            }

                        }
                        break;
                    }

                    case 4: //Dispatcher case
                    {

                        Dispatcher dispatcher = null;
                        loggedIn = false;
                        System.out.println("Are you a new dispatcher or an existing one?");
                        System.out.println("1. I am a new dispatcher. Create an account.");
                        System.out.println("2. I am an existing dispatcher. Log in.");
                        account = input.nextInt();
                        if (account == 2) {

                            dispatcher = dispatcherLogin();
                            if (dispatcher == null) {
                                break;
                            }
                        }

                        if (account == 1) {
                            dispatcher = dispatcherSignUp();
                            
                        }
                        

                        int openDisputes;
                        individualCase = 1;
                        int disputeNumber = 0;

                        while (individualCase != 0) {
                            openDisputes = 0;
                            for (int i = 0; i < disputes.size(); i++) {
                                if (disputes.get(i).checkStatus().equals( "Unsolved")) {
                                    openDisputes += 1;
                                }

                            }
                            System.out.println("There are " + openDisputes + " open disputes.");

                            System.out.println("1. Solve dispute");
                            System.out.println("0. Exit");
                            int dispatcherCase = 3;
                            
                            individualCase = input.nextInt();
                            if (individualCase == 1) {
                                if (openDisputes == 0) {
                                    System.out.println("There are no disputes open");
                                } else {

                                    for (int i = 0; i < disputes.size(); i++) {
                                        if (disputes.get(i).checkStatus() == "Unsolved") {
                                            disputeNumber = i;
                                            break;
                                        }
                                    }
                                    Dispute workingDispute = disputes.get(disputeNumber);
                                    System.out.println(workingDispute.getDescription());
                                    while (dispatcherCase != 0) {

                                        System.out.println("1. Get contact details");

                                        System.out.println("0. Dispute is solved");
                                        dispatcherCase = input.nextInt();
                                        if (dispatcherCase == 1) {
                                            dispatcher.getContact(workingDispute);
                                        } else if (dispatcherCase == 0) {
                                            dispatcher.solveDispute(workingDispute);

                                        }

                                    }

                                }
                            }

                        }

                    }
                }
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private User userLogin() {
        try {

            String username;
            String password;
            Connection con = getConnection();
            boolean accountFound = true;
            boolean userFound = false;
            User user = null;
            System.out.println("Enter your username:");
            username = input.next();
            if (!userUsernameExists(username)) {
                System.out.println("Username does not exist");
            } else {
                PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE users.username ='" + username + "'");
                ResultSet result = statement.executeQuery();
                result.next();
                String res = result.getString("userPass");
                //System.out.println(res);
                accountFound=false;

                System.out.println("Enter in your password. You have 3 tries");
                for (int j = 0; j < 3; j++) {
                    password = input.next();
                    if (password.equals(res)) {
                        System.out.println("Success");
                        accountFound = true;
                        user = new User(result.getString("userFName"), result.getString("username"), result.getString("userPass"), result.getString("userContact"));

                        
                        return user;

                    }
                }
            }

           

            return user;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private User userCreateAccount() throws Exception {
        try {
            Connection con = getConnection();
            User user = null;
            String name;
            boolean ok = true;
            String username = " ";
            String password;
            String contact;

            System.out.println("Enter your name");
            name = input.next();
            while (ok) {
                System.out.println("Enter a username");
                username = input.next();
                ok = false;

                if (userUsernameExists(username)) {
                    System.out.println("Username is already taken");
                    ok = true;
                }

            }

            System.out.println("Enter a password");
            password = input.next();
            System.out.println("Enter your phone number:");
            contact = input.next();
            try {
                PreparedStatement posting = con.prepareStatement("INSERT INTO users (userFName,username,userPass,userContact) VALUES ('" + name + "','" + username + "','" + password + "','" + contact + "')");
                posting.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
            //stmt.executeQuery("INSERT INTO users VALUES (name, username, password, contact);");
            user = new User(name, username, password, contact);

            
            con.close();
            return user;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    private boolean userUsernameExists(String username) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT username FROM users WHERE users.username ='" + username + "'");
            ResultSet result = statement.executeQuery();

            return result.next() != false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private Restaurant restaurantLogin() {
        try {
            Connection con = getConnection();
            String username;
            String password;
            boolean accountFound = true;
            boolean userFound = false;
            Restaurant res = null;
            System.out.println("Enter your username:");
            username = input.next();
            if (!restaurantUsernameExists(username)) {
                System.out.println("Username does not exist");
            } else {
                PreparedStatement statement = con.prepareStatement("SELECT * FROM restaurants WHERE restaurants.username ='" + username + "'");
                ResultSet result = statement.executeQuery();
                result.next();
                String passResult = result.getString("resPass");
                //System.out.println(passResult);
                accountFound=false;

                System.out.println("Enter in your password. You have 3 tries");
                for (int j = 0; j < 3; j++) {
                    password = input.next();
                    if (password.equals(passResult)) {
                        System.out.println("Success");
                        accountFound = true;
                        res = new Restaurant(result.getString("username"), result.getString("resNmae"), result.getString("resPass"), result.getString("resLocation"), result.getString("resContact"));

                        
                        return res;

                    }
                }
            }
            

            return res;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    private Restaurant restaurantSignUp() {
        try {
            Connection con = getConnection();
            Restaurant res;
            String username = " ";
            String name;
            String password;
            String location;
            String contact;
            boolean ok = true;

            System.out.println("Enter your name");
            name = input.next();

            while (ok) {
                System.out.println("Enter a username");
                username = input.next();
                ok = false;
                if (restaurantUsernameExists(username)) {
                    System.out.println("Username already exists");
                    ok = true;
                }

            }

            System.out.println("Enter a password");
            password = input.next();
            System.out.println("Enter your phone number:");
            contact = input.next();
            System.out.println("Enter your location:");
            location = input.next();
            try {
                PreparedStatement posting = con.prepareStatement("INSERT INTO restaurants (username,resNmae,resPass,resLocation,resContact,isOpen) VALUES ('" + username + "','" + name + "','" + password + "','" + location + "','" + contact + "',true)");
                posting.executeUpdate();

            } catch (Exception e) {
                System.out.println(e);
            }
            res = new Restaurant(name, username, password, location, contact);
            
            con.close();
            return res;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private boolean restaurantUsernameExists(String username) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT username FROM restaurants WHERE restaurants.username ='" + username + "'");
            ResultSet result = statement.executeQuery();

            return result.next() != false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private Delivery deliveryLogin() {
        try {
            Connection con = getConnection();
            String username;
            String password;
            boolean accountFound = true;
            Delivery delivery = null;
            boolean userFound = false;
            System.out.println("Enter your username:");
            username = input.next();
            if (!deliveryUsernameExists(username)) {
                System.out.println("Username does not exist");
            } else {
                PreparedStatement statement = con.prepareStatement("SELECT * FROM deliverymen WHERE deliverymen.username ='" + username + "'");
                ResultSet result = statement.executeQuery();
                result.next();
                String passResult = result.getString("delPass");
                //System.out.println(passResult);
                accountFound=false;

                System.out.println("Enter in your password. You have 3 tries");
                for (int j = 0; j < 3; j++) {
                    password = input.next();
                    if (password.equals(passResult)) {
                        System.out.println("Success");
                        accountFound = true;
                        delivery = new Delivery(result.getString("delName"),result.getString("username"), result.getString("delPass"), result.getString("delContact"));

                        
                        return delivery;

                    }
                }
            }
            if (accountFound == false) {
                System.out.println("You have failed entering your password");
                return null;
            }
            return delivery;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private Delivery deliverySignUp() {
        try {
            Connection con = getConnection();
            Delivery delivery = null;
            String name;
            String username = " ";
            String password;
            String contact;
            boolean ok = true;

            System.out.println("Enter your name");
            name = input.next();

            while (ok) {
                System.out.println("Enter a username");
                username = input.next();
                ok = false;
                if (deliveryUsernameExists(username)) {
                    ok = true;
                    System.out.println("Username already exists!");
                }

            }

            System.out.println("Enter a password");
            password = input.next();
            System.out.println("Enter your phone number:");
            contact = input.next();
            delivery = new Delivery(name, username, password, contact);
            try {
                PreparedStatement posting = con.prepareStatement("INSERT INTO deliverymen (username,delName,delPass,delContact,delCurrentOrder) VALUES ('" + username + "','" + name + "','" + password + "','" + contact + "' , FALSE)");
                posting.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
            
            con.close();
            return delivery;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private boolean deliveryUsernameExists(String username) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT username FROM deliverymen WHERE username ='" + username + "'");
            ResultSet result = statement.executeQuery();

            return result.next() != false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private Dispatcher dispatcherLogin() {
        try {
            Connection con = getConnection();
            String username;
            String password;
            boolean accountFound = true;
            
            Dispatcher dispatcher = null;
            System.out.println("Enter your username:");
            username = input.next();
            if (!deliveryUsernameExists(username)) {
                System.out.println("Username does not exist");
            } else {
                PreparedStatement statement = con.prepareStatement("SELECT * FROM dispatchers WHERE username ='" + username + "'");
                ResultSet result = statement.executeQuery();
                result.next();
                System.out.println("here");
                String passResult = result.getString("disPass");
                //System.out.println(passResult);
                accountFound=false;

                System.out.println("Enter in your password. You have 3 tries");
                for (int j = 0; j < 3; j++) {
                    password = input.next();
                    if (password.equals(passResult)) {
                        System.out.println("Success");
                        accountFound = true;
                        dispatcher = new Dispatcher(result.getString("username"), result.getString("disName"), result.getString("disPass"), result.getString("disContact"));

                        
                        return dispatcher;

                    }
                }
            }
           

            return dispatcher;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private Dispatcher dispatcherSignUp() {
        try {
            Connection con = getConnection();
            Dispatcher dispatcher = null;
            String name;
            String username = " ";
            String password;
            String contact;
            boolean ok = true;

            System.out.println("Enter your name");
            name = input.next();

            while (ok) {
                System.out.println("Enter a username");
                username = input.next();
                ok = false;
                if (dispatcherUsernameExists(username)) {
                    ok = true;
                    System.out.println("Username already exists");
                }

            }

            System.out.println("Enter a password");
            password = input.next();
            System.out.println("Enter your phone number:");
            contact = input.next();
            try {
                PreparedStatement posting = con.prepareStatement("INSERT INTO dispatchers (username,disName,disPass,disContact) VALUES ('" + username + "','" + name + "','" + password + "','" + contact + "')");
                posting.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
            dispatcher = new Dispatcher(name, username, password, contact);
            
            con.close();
            return dispatcher;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private boolean dispatcherUsernameExists(String username) {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT username FROM dispatchers WHERE username ='" + username + "'");
            ResultSet result = statement.executeQuery();

            return result.next() != false;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
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
