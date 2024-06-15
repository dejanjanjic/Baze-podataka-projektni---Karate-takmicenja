package net.etfbl.bp.karatetakmicenja.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.Application;

import java.io.IOException;


public class MainWindowController {
    @FXML
    private MenuItem competitionMenuItem;

    @FXML
    private MenuItem competitorMenuItem;

    @FXML
    private StackPane mainStackPane;

    public void initialize(){
        mainStackPane.setStyle("-fx-background-image: url('karate_background.jpg'); -fx-background-size: cover; -fx-opacity: 0.5;");

    }

    @FXML
    void competitionMenuItemClicked(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competition_codebook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 420);
        stage.setTitle("Šifarnik takmičenja");
        stage.setScene(scene);

        stage.setX(500);
        stage.setY(200);

        stage.show();
    }

    @FXML
    void competitorMenuItemClicked(ActionEvent event) {
        System.out.println("radi2");
    }
}
