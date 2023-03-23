package com.pt2022_30423_chete_doru_assignment_3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/** JavaFX application. The stage and the scene get initialized here.
 */
public class Application extends javafx.application.Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Products.fxml"));
        scene = new Scene(fxmlLoader.load(), 654, 554);
        stage.setTitle("Orders Management");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The scene can be changed to see another window.
     * @param fxmlFileName
     * @throws IOException
     */
    public static void changeWindow(String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlFileName));
        scene.setRoot(fxmlLoader.load());
    }

    public static void main(String[] args) {
        launch();
    }
}