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
public class transaction {
    int id;
    String date,ename,description,type,purchase_sale_id;
    Double amount;
    public transaction(int id,String date,String ename,Double amount,String type,String description,String purchase_sale_id){
        this.id=id;
        this.description=description;
        this.ename=ename;
        this.date=date;
        this.type=type;
        this.amount=amount;
        this.purchase_sale_id=purchase_sale_id;
    }

    public String getPurchase_sale_id() {
        return purchase_sale_id;
    }

    public void setPurchase_sale_id(String purchase_sale_id) {
        this.purchase_sale_id = purchase_sale_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
