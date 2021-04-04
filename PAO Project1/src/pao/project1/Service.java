/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Service {
        public List<Restaurant> restaurants=new ArrayList<Restaurant>();

    Service(){
        
        Scanner input = new Scanner(System.in);
         
        
            int x=1;
        while(x != 0)
        {System.out.println("Hello! Are you: 1. a User, 2. a Restaurant, 3. a Delivery man or 4. a Dispecer? 0->Exit");
        x=input.nextInt();
        
        switch(x){
         
        case 1:
            System.out.println("Enter Your Name, and phone number");
                    User user = new User (input.next(),input.next(),restaurants);
        while (x!=0){
        System.out.println("1. Place Order");
        System.out.println("2. Cancel Order");
        System.out.println("3. Open Dispute");
        System.out.println("0. Exit");
            x = input.nextInt();
            switch(x){
                case 1:
                {
                    if (restaurants.size()>0)
                    user.placeOrder();
                    else System.out.println("There are no restaurants available. Please set restaurants to open or create a new one.");
                }
                case 2:
                {
                    user.cancelOrder();
                }
                case 3:
                {
                    user.openDispute();
                }
                
            }
        break;}
        case 2:
            
            System.out.println("Enter Your Restaurant name, location and phone number");
                    Restaurant res = new Restaurant (input.nextLine(),input.nextLine(),input.nextLine());
                            restaurants.add(res);
                            Menu menu = new Menu();
                      while(x!=0)
            {      
        System.out.println("1. Set Open");
        System.out.println("2. Add an item to the menu");
        System.out.println("3. Remove an item from the menu");
        System.out.println("0. Exit");
         x = input.nextInt();
            switch(x){
                case 1:
                    System.out.println("0 for Closed, 1 for Open");
                    boolean ok;
                    if (input.nextInt() ==0)
                        res.setOpen(true);
                    else res.setOpen(false);
                  
                    break;
                case 2:
                    System.out.println("Enter name, id and price for the item");
                    menu.addItem(input.next(),input.nextInt(),input.nextDouble());
                    break;
                case 3:
                    System.out.println("Which item do you want to remove?");
                    menu.showItems();
                    menu.removeItem(input.nextInt());
                    break;
                    
        }}
        x=1;
    }
                }
            
        }
        
        } 
