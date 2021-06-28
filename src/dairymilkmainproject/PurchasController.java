package dairymilkmainproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PurchasController implements Initializable {

    @FXML
    public DatePicker date;
    public TextField staff_id,address,qty,contact,sname,rate,total;
    public ComboBox milkOf;
    public TableView purchase_table;
    public TableColumn table_id,table_sname,table_staffid,table_date,table_contact,table_address,table_milktype,table_qty,table_rate,table_total;
    int id=1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        milkOf.getItems().addAll("cow","buffalo","camel");
        try {
            showTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PurchasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PurchasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public int getcurrId() throws ClassNotFoundException, SQLException{
        Connection con1;
        PreparedStatement insert;
        Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con1.prepareStatement("select * from purchase");
            ResultSet rs1=insert.executeQuery();
            
            int currId=0;
            while(rs1.next()){
//               pch=new Purchase(Integer.parseInt(rs1.getString(1)),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),Integer.parseInt(rs1.getString(8)),Double.parseDouble(rs1.getString(9)),Double.parseDouble(rs1.getString(10)));
//                products.add(pch);
                currId=Integer.parseInt(rs1.getString(1));
                
            }
            return currId+1;
    }
    
    public boolean check_staff_id(String staffId) throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement insert;
        Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con.prepareStatement("select staffid from staff");
            ResultSet rs=insert.executeQuery();
            boolean flag=false;
            System.out.println(staffId);
            while(rs.next()){
//                System.out.println(rs.getString(1));
                if(rs.getString(1).compareTo(staffId)==0){
                    flag=true;
                }
            }
            return flag;
    }
    
    
    @FXML
    public void updateTable(ActionEvent event) throws ClassNotFoundException, SQLException{
        if(check_staff_id(staff_id.getText())){
        id=getcurrId();
        
        Connection con1;
        PreparedStatement insert;
        Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            
            insert=con1.prepareStatement("insert into purchase values(?,?,?,?,?,?,?,?,?,?)");
            
            insert.setInt(1,id);
            insert.setString(2,date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            insert.setString(3,staff_id.getText());
            insert.setString(4,sname.getText());
            insert.setString(5,contact.getText());
            insert.setString(6,address.getText());
            insert.setString(7,milkOf.getSelectionModel().getSelectedItem().toString());
            insert.setInt(8,Integer.parseInt(qty.getText()));
            insert.setDouble(9,Double.parseDouble(rate.getText()));
            insert.setDouble(10, Double.parseDouble(total.getText()));
            insert.executeUpdate();
            showTable();
            
            //updating the stock table
//            insert=con1.prepareStatement("update stock set qty=? where milktype=?");
            insert=con1.prepareStatement("select qty from stock where milktype=?");
            insert.setString(1,milkOf.getSelectionModel().getSelectedItem().toString());
            ResultSet rs=insert.executeQuery();
            int qty_table=0;
            if(rs.next()){
                qty_table=rs.getInt(1);
            }
            
            insert=con1.prepareStatement("update stock set qty=? where milktype=?");
            insert.setInt(1, qty_table+Integer.parseInt(qty.getText()));
            insert.setString(2,milkOf.getSelectionModel().getSelectedItem().toString());
            insert.executeUpdate();
            System.out.println("updated in stock");
            
            
            //updating the transaction table
            int transaction_id=getTransactionId();
            
            insert=con1.prepareStatement("insert into transaction value(?,?,?,?,?,?,?)");
            insert.setInt(1,transaction_id);
            insert.setString(2,date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            insert.setString(3,sname.getText());
            insert.setDouble(4,Double.parseDouble(total.getText()));
            insert.setString(5, "debit");
            insert.setString(6,"purchase of milk");
            insert.setString(7,"purchase_"+Integer.toString(id));
            insert.executeUpdate();
            System.out.println("updated in transaction");
        }
        else{
            JOptionPane.showMessageDialog(null,"enter correct staff id");
        }
    }
    
    public int getTransactionId() throws ClassNotFoundException, SQLException{
        Connection con;
        PreparedStatement insert;
        Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con.prepareStatement("select * from transaction");
            ResultSet rs=insert.executeQuery();
            int temp=0;
            while(rs.next()){
                temp++;
            }
            return temp+1;
    }
    
    public void showTable() throws ClassNotFoundException, SQLException{
        Purchase pch;
        Connection con1;
        PreparedStatement insert;
        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        table_staffid.setCellValueFactory(new PropertyValueFactory<>("staffid"));
        
        table_sname.setCellValueFactory(new PropertyValueFactory<>("seller_name"));
        
        table_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        
        table_rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        
        table_total.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        
        table_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        table_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
        table_milktype.setCellValueFactory(new PropertyValueFactory<>("milktype"));
        
        ObservableList<Purchase> products=FXCollections.observableArrayList();
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con1.prepareStatement("select * from purchase");
            ResultSet rs1=insert.executeQuery();
            while(rs1.next()){
               pch=new Purchase(Integer.parseInt(rs1.getString(1)),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),Integer.parseInt(rs1.getString(8)),Double.parseDouble(rs1.getString(9)),Double.parseDouble(rs1.getString(10)));
                products.add(pch);
            }
        purchase_table.setItems(products);
    }
    
    
    @FXML
    public void delete_record(ActionEvent event) throws ClassNotFoundException, SQLException{
        Connection con1;
        PreparedStatement insert;
        Purchase st=(Purchase)purchase_table.getSelectionModel().getSelectedItems().get(0);        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con1.prepareStatement("delete from purchase where id = ?");
            insert.setInt(1,st.getId());
            insert.executeUpdate();
            
            
            
            showTable();
    }
    
    @FXML
    public void goToStaff(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("staff.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
    @FXML
    public void goToSales(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Sell.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
    
    public void goToTransaction(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
    
    public void goToStock(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Stock.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
    
    @FXML
    public void setRate(ActionEvent event){
        if(milkOf.getSelectionModel().getSelectedItem().toString()=="cow"){
            rate.setText(Integer.toString(48));
        }
        else if(milkOf.getSelectionModel().getSelectedItem().toString()=="buffalo"){
            rate.setText(Integer.toString(60));
        }
        else if(milkOf.getSelectionModel().getSelectedItem().toString()=="camel"){
            rate.setText(Integer.toString(80));
        }
        int x=Integer.parseInt(qty.getText());
        double y=Double.parseDouble(rate.getText());
        double z=x*y;
        total.setText(Double.toString(z));
    }
}
