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
public class Menu{
    protected List<Item> availableItems=new ArrayList<Item>();
        public void showItems(){
            for (int i=0; i<availableItems.size();i++)
            {
                System.out.println(availableItems.get(i).getName());
        
            }}
        public List<Item> getItems(){
            return availableItems;
        }
        public void addItem (String name, int id, Double price) {
            Item newItem = new Item(name, price);
            availableItems.add(newItem);
        }    
        public void removeItem(int nr) {
        availableItems.remove(nr);
    }
    }
