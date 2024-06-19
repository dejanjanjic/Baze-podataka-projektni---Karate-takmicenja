package net.etfbl.bp.karatetakmicenja;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 420);
        stage.setTitle("KARATE IS");
        stage.setScene(scene);
        stage.getIcons().add(new Image("karate-icon.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}