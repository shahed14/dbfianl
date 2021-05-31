/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfinal;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField username_text;
    @FXML
    private TextField password_text;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    Statement statement;
    Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* try {
           // Connection connection = DBconnection.get_connection();
            statement = connection.createStatement();
                 String sql="'select * from users where Email = ? and Password = ?";
                 ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.println("founded");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }*/
      
    }    

    @FXML
    private void loginButton_handle(ActionEvent event) throws SQLException, IOException {
       
        try {
            Window owner = loginButton.getScene().getWindow();
            
            System.out.println(username_text.getText());
            System.out.println(password_text.getText());
            
            if(username_text.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter your email id");
                return;
            }
            if(password_text.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                        "Please enter a password");
                return;
            }
            
            String emailId = username_text.getText();
            String password = password_text.getText();
              
            
            DBconnection jdbcDao = new DBconnection();
            boolean flag = jdbcDao.validate(emailId, password);
           
            if(!flag) {
                infoBox("Please enter correct Email and Password", null, "Failed");
            }else {
                infoBox("Login Successful!", null, "Failed");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
        
    

    @FXML
    private void registerButton_handle(ActionEvent event) {
       
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
           if(input.length() <7){
               return true;
           }
           return false;
     }
      public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
    
     
}
