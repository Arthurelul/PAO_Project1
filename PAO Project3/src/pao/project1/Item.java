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
public class Item {

    private String name;
   
    private Double price;

    Item(String newName, Double newPrice) {
        name = newName;
        
        price = newPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

   

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double newPrice) {
        price = newPrice;
    }

}
