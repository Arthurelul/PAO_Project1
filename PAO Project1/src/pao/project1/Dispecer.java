/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pao.project1;

/**
 *
 * @author Arthur
 */
public class Dispecer{
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
        
        public void solveDispute(Dispute dispute){
          String userContact = dispute.getOrder().getUser().getContact();
          String deliveryContact = dispute.getOrder().getDelivery().getContact();
          
          dispute.updateStatus("Solved");
        } 
        
    }
