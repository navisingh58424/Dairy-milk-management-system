/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dairymilkmainproject;

/**
 *
 * @author AYAN
 */
public class Stock {
    int id,qty;
    String milktype;
    public Stock(int id,String milktype,int qty){
        this.id=id;
        this.milktype=milktype;
        this.qty=qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMilktype() {
        return milktype;
    }

    public void setMilktype(String milktype) {
        this.milktype = milktype;
    }
    
}
