/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.util.Scanner;

/**
 *
 * @author Arthur
 */
public class Delivery{
    Scanner input = new Scanner(System.in);
        private String name;
        private String contact;
        
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
        public void acceptDelivery (Order order){
        order.updateStatus("Being delivered");
        order.setDelivery(this);
        }
        public void setAsDelivered(Order order){
        order.updateStatus("Delivered!");
        }
        public void openDispute( Order order){
            String problem = input.nextLine();
            Dispute dispute = new Dispute("Unsolved", problem, order, "User");
            
        }
    }
