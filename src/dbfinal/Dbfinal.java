/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfinal;

import java.io.File;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Dbfinal extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }         

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader();
      
        loader.setController(new LoginPageController());
        Parent root=FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        Parent root2 = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene2 = new Scene(root2);
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Screen");
        primaryStage.show();
    }
    }
    

