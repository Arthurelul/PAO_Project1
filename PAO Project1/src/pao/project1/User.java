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
public class User{
    Scanner input = new Scanner(System.in);
        private String name; 
        private String contact;
        private Order order;
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        User(String newName, String newContact,List<Restaurant> newRes)
        {   restaurants=newRes;
            contact = newContact;
            name= newName;
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
        
        public void placeOrder(){
            System.out.println("Chose a restaurant:");
                    for(int i = 0; i<restaurants.size();i++)
                    {System.out.print(i);
                    System.out.print(". ");
                        System.out.println(restaurants.get(i).getName());}
             int x= input.nextInt();
             Restaurant res = restaurants.get(x);
            System.out.println("Chose Items:(numbers followed by enter, end with 0)");
               x=1;
               res.showItems();
               List<Item> items = new ArrayList<Item>();
               while(x!=0)
               {items.add(res.getItems().get(x));
                 
               }
              
            order = new Order(this,items,restaurants.get(x) );
        }
        
        public void cancelOrder(){
            order.updateStatus("Canceled");
        }
        
        public void openDispute(){
            String problem = input.nextLine();
            Dispute dispute = new Dispute("Unsolved", problem, order, "Delivery");
        }
        
        public void payOrder(){
            order.paidOrder();
            
        }
        
    }
