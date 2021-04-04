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
public class  Restaurant extends Menu{
    
        private String name;
        private String location;
        private String contact;
        Restaurant(String newName, String newLocation,String newContact)
        {
            name= newName;
            location = newLocation;
            contact= newContact;
        }
        
        
        List<Integer> reviews=new ArrayList<Integer>();
        boolean isOpen;
        
        public String getName() {
           return name;
        }
        public void setName(String newName) {
           name = newName;
        }
        
        public String getLocation() {
           return location;
        }
        public void setLocation(String newLocation) {
           location = newLocation;
        }
        
        public String getContact() {
           return contact;
        }
        public void setContact(String newContact) {
           contact = newContact;
        }
        
        public int getRating() {
            Integer sum = reviews.stream()
            .mapToInt(Integer::intValue)
            .sum();
            
            return sum/reviews.size();
        }
        
        public boolean checkOpen(){
            return isOpen;
        }
        
        public void setOpen(boolean open){
            isOpen=open;
        }  
          
    }
