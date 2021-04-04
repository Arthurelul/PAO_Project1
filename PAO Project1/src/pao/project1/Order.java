/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class Order{
        List<Item> items=new ArrayList<Item>();
        
        private int id;
        private boolean isPaid = false;
        private String status;
        private int price;
        private Delivery delivery;
        private User user;
        private Restaurant restaurant;
        Order(User newUser,List<Item> newItems,Restaurant newRestaurant){
            restaurant = newRestaurant;
            user = newUser;
            items = newItems;
        }
        
        public int getId() {
           return id;
        }
        public User getUser(){
            return user;
        }
        
        public String getStatus(){
            return status;
        }
        public void updateStatus(String newStatus){
            status = newStatus;
        }
        public void addItem(Item item){
            items.add(item);
        }
        public void removeItem (Item item){
            items.remove (item);
        }
        public Double getPrice () {
            Double totalPrice=0.0;
            for (int i=0;i<items.size();i++)
            {
                totalPrice+=items.get(i).getPrice();
            }
            return totalPrice;
        }
        public Delivery getDelivery() {
           return delivery;
        }
        public void setDelivery(Delivery newDelivery) {
           delivery = newDelivery;
        }
        public void paidOrder(){
            isPaid = true;
        }
    }
