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
public class Dispute {

    private String status;
    private String description;
    private String type;
    private Order order;

    Dispute(String Status, String Description, Order newOrder, String Type) {
        status = Status;
        description = Description;
        type = Type;
        order = newOrder;
    }

    public void updateStatus(String newStatus) {
        status = newStatus;
    }

    public String checkStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Order getOrder() {
        return order;
    }
}
