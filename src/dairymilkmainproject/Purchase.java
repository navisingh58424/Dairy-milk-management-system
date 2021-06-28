package dairymilkmainproject;

public class Purchase {
    int id,qty;
    String date;
    String staffid;
    String seller_name,contact,address,milktype;
    double rate,totalAmount;
    public Purchase(int id,String date,String staffid,String seller_name,String contact,String address,String milktype,int qty,double rate,double totalAmount){
        this.id=id;
        this.date=date;
        this.qty=qty;
        this.staffid=staffid;
        this.seller_name=seller_name;
        this.contact=contact;
        this.address=address;
        this.milktype=milktype;
        this.totalAmount=totalAmount;
        this.rate=rate;
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    
    
}
