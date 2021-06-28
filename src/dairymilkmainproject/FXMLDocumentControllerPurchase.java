package dairymilkmainproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class FXMLDocumentControllerPurchase implements Initializable {
    Connection con1,con2;
    PreparedStatement insert,insert1;
    Staff staff;
    @FXML
    public TextField name,id,contact,address;
    public ComboBox gender;
    public DatePicker datepicker;
    public TableView staff_table;
    public TableColumn table_id,table_name,table_date,table_address,table_contact,table_gender;
    LocalDate date2000;
    
    
    @FXML
    private void deleteStaff(ActionEvent event) throws ClassNotFoundException, SQLException {
            Staff st=(Staff)staff_table.getSelectionModel().getSelectedItems().get(0);        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert=con1.prepareStatement("delete from staff where staffid = ?");
            insert.setString(1,st.getId());
            insert.executeUpdate();
            
            updateTable();
            
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.getItems().addAll("Male","female","other");
        gender.setValue("select gender");
        date2000=LocalDate.of(2000,1,1);
        datepicker.setValue(date2000);
        try {
            updateTable();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentControllerPurchase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentControllerPurchase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    @FXML
    public void addStaff(ActionEvent event) throws ClassNotFoundException, SQLException{
        String str=gender.getSelectionModel().getSelectedItem().toString();
        String date = datepicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            
            insert=con1.prepareStatement("insert into staff values(?,?,?,?,?,?)");
            insert.setString(1,id.getText());
            insert.setString(2,date);
            insert.setString(3,name.getText());
            insert.setString(4,str);
            insert.setString(5,contact.getText());
            insert.setString(6,address.getText());
            
            insert.executeUpdate();
            System.out.println("updated");
            
            updateTable();
    }
    
    
    
    
    public void updateTable() throws ClassNotFoundException, SQLException{
        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));//use this data for this column
        table_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        table_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        table_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        ObservableList<Staff> products=FXCollections.observableArrayList();
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            insert1=con2.prepareStatement("select * from staff");
            ResultSet rs1=insert1.executeQuery();
            while(rs1.next()){
               staff=new Staff(rs1.getString(1),rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6));
                products.add(staff);
            }
        staff_table.setItems(products);
    }
    @FXML
    public void editStaff(ActionEvent event) throws ClassNotFoundException, SQLException{
        
       if(id.getText().length()==0){
           JOptionPane.showMessageDialog(null, "enter the id");
       }
       else{
          staff=new Staff("0","0","0","0","0","0");
          if(name.getText().length()!=0){
              staff.setName(name.getText());
          }
          if(id.getText().length()!=0){
              staff.setId(id.getText());
          }
          if(address.getText().length()!=0){
              staff.setAddress(address.getText());
          }
          if(contact.getText().length()!=0){
              staff.setContact(contact.getText());
          }
          if(gender.getSelectionModel().getSelectedItem().toString()!="select gender"){
              staff.setGender(gender.getSelectionModel().getSelectedItem().toString());
          }
          if(datepicker.getValue()!=date2000){
              staff.setDate(datepicker.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString());
          }
          
                   
//            insert=con1.prepareStatement("insert into staff values(?,?,?,?,?,?)");
          
          if(staff.getName()!="0"){
              Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
    
              insert=con1.prepareStatement("update staff set name=? where staffid=?");
              insert.setString(1,staff.getName());
              insert.setString(2,staff.getId());
              insert.executeUpdate();
          }
          
          if(staff.getAddress()!="0"){
              Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
    
              insert=con1.prepareStatement("update staff set address=? where staffid=?");
              insert.setString(1,staff.getAddress());
              insert.setString(2,staff.getId());
              insert.executeUpdate();
          }
          
          if(staff.getContact()!="0"){
              Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
    
              insert=con1.prepareStatement("update staff set contact=? where staffid=?");
              insert.setString(1,staff.getContact());
              insert.setString(2,staff.getId());
              insert.executeUpdate();
          }
          
          if(staff.getDate()!="0"){
              Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
              insert=con1.prepareStatement("update staff set date=? where staffid=?");
              insert.setString(1,staff.getDate());
              insert.setString(2,staff.getId());
              insert.executeUpdate();
          }
          
          if(staff.getGender()!="0"){
              Class.forName("com.mysql.cj.jdbc.Driver");
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
              insert=con1.prepareStatement("update staff set gender=? where staffid=?");
              insert.setString(1,staff.getGender());
              insert.setString(2,staff.getId());
              insert.executeUpdate();
          }
          updateTable();
        }
    }
   
    @FXML
    public void goToPurchase(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Purchas.fxml"));
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
    
    
}
