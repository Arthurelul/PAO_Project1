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
public class Dispatcher {

    private String name;
    private String contact;
    private String password;
    private String username;
    

    Dispatcher(String newName,String newUsername,String newPassword, String newContact) {
        name = newName;
        password = newPassword;
        username = newUsername;
        contact = newContact;
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

    public void getContact(Dispute dispute) {
        if (dispute.getOrder() != null) {
            String userContact = dispute.getOrder().getUser().getContact();
            String deliveryContact = dispute.getOrder().getDelivery().getContact();
            System.out.println("User contact: " + userContact);
            System.out.println("Delivery contact: " + deliveryContact);
        }
        else {
            System.out.println("There is no order for this dispute.");
        }
    }

    public String getProblem(Dispute dispute) {
        return dispute.getDescription();
    }

    public void solveDispute(Dispute dispute) {
        dispute.updateStatus("Solved");
        
    }

}
