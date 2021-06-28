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
public class SellMilk {
    int id,qty;
    String Date,dealerName,staffid,milktype;
    double rate,total;
    public SellMilk(int id,String Date,String staffid,String dealerName,String milktype,int qty,double rate,double total){
        this.id=id;
        this.qty=qty;
        this.Date=Date;
        this.dealerName=dealerName;
        this.staffid=staffid;
        this.milktype=milktype;
        this.rate=rate;
        this.total=total;
//        this.
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

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getMilktype() {
        return milktype;
    }

    public void setMilktype(String milktype) {
        this.milktype = milktype;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
