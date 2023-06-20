package com.example.calendar3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController{
    public static String username;
    public static String password;
    FXMLLoader loader;
    Parent root;
    Scene scene;
    Stage stage;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private TextField usernameTextField;


    public void initialize(){
        loginButton.setOnAction(e -> loginButtonAction(e));
    }
    public void loginButtonAction(ActionEvent e) {

        if(usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false)
        {
           // loginMessageLabel.setText("Giriş yapmaya çalışılıyor.");
            username = usernameTextField.getText();
            password = passwordPasswordField.getText();
          //  int gir = Baglanti.Login(user,pass);
            int gir =1;
            if(gir== 1){
                loginMessageLabel.setText("Giriş başarılı.");
                try {
                    loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                    root = loader.load();
                    scene = new Scene(root);
                   // scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.show();


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else{
                loginMessageLabel.setText("Kullanıcı adı veya şifre hatalı.");
            }
        }else {
            loginMessageLabel.setText("TC no ve şifre giriniz.");
        }


    }

}
