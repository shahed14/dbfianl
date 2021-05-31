/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfinal;

import static dbfinal.LoginPageController.infoBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField nameTextFeild;
    @FXML
    private TextField emailTextFeild;
    @FXML
    private TextField mobileTextFeild;
    @FXML
    private TextField passwordTextFeild;
    @FXML
    private Button RegisterButton;
    @FXML
     
    private TextField RoleTextFeild;

    private Button ResetButton;
    Statement statement = null;
    private static final String insert_query = "INSERT INTO users (Name , Email , Mobile ,Password , Role)VALUES(?,?,?,?,?)";
    Connection connection = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            connection = DBconnection.get_connection();
          //  statement = connection.createStatement();
            
        } catch (Exception ex) {
                  System.out.println(ex);
        }

    }    

    @FXML
    private void RegisterButton_handle(ActionEvent event) {
     Window owner = RegisterButton.getScene().getWindow();

        String name_input = nameTextFeild.getText();
        String email_input = emailTextFeild.getText();
        String mobile_input = mobileTextFeild.getText();
        String password_input = passwordTextFeild.getText();
        String Role_input = RoleTextFeild.getText();
        
         if(name_input.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "register Error!",
                        "Please enter your name ");
                return;
            }
         if(email_input.isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "register Error!", "pleae entre your email");
             return;
         }
         if(mobile_input.isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "register Error!", "please entre your mobile");
             return;
         }
         if(password_input.isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "register Error!", "please entre your password");
             return;
         }
         if(Role_input.isEmpty()){
             showAlert(Alert.AlertType.ERROR, owner, "register Error!", "please entre your role");
             return;
         }
        if(validate_input(name_input)&& validate_input(email_input)&&
                validate_numeric_val(password_input)&&validate_password(password_input)&& validate_numeric_val(mobile_input)
                &&validate_input(Role_input)){
          
           //  insertRow(name_input, email_input, mobile_input, password_input, Role_input);
            
                }
           
    }

    @FXML
    private void ResetButton_handle(ActionEvent event) {
        nameTextFeild.setText("");
        emailTextFeild.setText("");
        passwordTextFeild.setText("");
        mobileTextFeild.setText("");
        RoleTextFeild.setText("");
        
    }
     private boolean validate_input(String input){
        return !input.equals("");
    }
     private boolean validate_numeric_val(String input){
        if(validate_input(input)){
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
        }
        return false;
    }
     private boolean validate_password(String input){
           if(input.length() < 7){
               return false;
           }
           return true;
     }
       public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
        private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
        public void insertRow(String Name , String Email , String Mobile , String Password , String Role){
        try {
            PreparedStatement preparedstatement2 = connection.prepareStatement(insert_query);
            preparedstatement2.setString(1, Name);
             preparedstatement2.setString(2, Email);
            preparedstatement2.setString(3, Mobile);
            preparedstatement2.setString(4, Password);
            preparedstatement2.setString(5, Role);
            System.out.println(preparedstatement2);
          int excutequery =   preparedstatement2.executeUpdate();
             if(excutequery>0){
                 infoBox("Added Successfully", null, "SUCCESS");
             }
               else{
            infoBox("Added Failed!", null, "Failed");

           
        }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
            
        }
}
