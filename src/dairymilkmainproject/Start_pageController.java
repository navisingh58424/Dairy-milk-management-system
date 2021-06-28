/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dairymilkmainproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AYAN
 */
public class Start_pageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField txt;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(MysqlPassword.password);
    }    
    
    @FXML
    public void updatePassword(ActionEvent event){
        String s=txt.getText();
        if(s==null){
            JOptionPane.showMessageDialog(null,"enter your mysql password");
        }
        else{
            MysqlPassword.setPassword(s);
        }
        }
    
    @FXML
    public void goToTransaction(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Transaction.fxml"));
        Scene sc=new Scene(StaffParent);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sc);
        window.show();
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
    
    
    public void goToStock(ActionEvent event) throws IOException{
        Parent StaffParent=FXMLLoader.load(getClass().getResource("Stock.fxml"));
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
