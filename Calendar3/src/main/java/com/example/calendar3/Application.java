package com.example.calendar3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Application extends javafx.application.Application {
    @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
