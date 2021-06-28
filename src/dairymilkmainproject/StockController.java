/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dairymilkmainproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AYAN
 */
public class StockController implements Initializable {
    
    @FXML
    public TableView stock_table;
    public TableColumn table_id,table_milktype,table_qty;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Stock pch;
            Connection con1;
            PreparedStatement insert;
            table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            table_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            
            table_milktype.setCellValueFactory(new PropertyValueFactory<>("milktype"));
            
            ObservableList<Stock> products=FXCollections.observableArrayList();
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            
            con1=DriverManager.getConnection("jdbc:mysql://localhost/dairyMilk","root",MysqlPassword.password);
            
            insert=con1.prepareStatement("select * from stock");
            ResultSet rs1=insert.executeQuery();
            while(rs1.next()){
                pch=new Stock(Integer.parseInt(rs1.getString(1)),rs1.getString(2),Integer.parseInt(rs1.getString(3)));
                products.add(pch);
            }
            stock_table.setItems(products);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
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
    
    @FXML
    public void goToTransaction(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
    
    public void goToPurchase(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Purchas.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
    }
    
}
