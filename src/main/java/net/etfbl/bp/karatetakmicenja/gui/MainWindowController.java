package net.etfbl.bp.karatetakmicenja.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.Application;
import net.etfbl.bp.karatetakmicenja.dao.TakmicenjeDAO;
import net.etfbl.bp.karatetakmicenja.gui.competition.CompetitionDetailsController;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainWindowController {
    @FXML
    private MenuItem competitionMenuItem;

    @FXML
    private MenuItem competitorMenuItem;

    @FXML
    private StackPane mainStackPane;
    @FXML
    private Menu takmicenjaMenuField;
    private List<MenuItem> takmicenjaMenuItems = new ArrayList<>();

    public void initialize(){
        mainStackPane.setStyle("-fx-background-image: url('karate_background.jpg'); -fx-background-size: cover; -fx-opacity: 0.5;");
        TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
        List<Takmicenje> takmicenjeList = takmicenjeDAO.selectAll();
        MenuItem menuItem;
        for (Takmicenje t :
                takmicenjeList) {
            menuItem = new MenuItem(t.getIdTakmicenja() + " " + t.getNaziv());
            takmicenjaMenuField.getItems().add(menuItem);
            takmicenjaMenuItems.add(menuItem);
            menuItem.setOnAction(event -> openCompetitionDetails(t));
        }

    }

    private void openCompetitionDetails(Takmicenje takmicenje) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competition_details.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);

            // Dobijemo kontroler nove scene i proslijedimo mu podatke
            CompetitionDetailsController controller = fxmlLoader.getController();
            controller.initData(takmicenje);

            stage.setTitle("Detalji takmičenja");
            stage.setScene(scene);
            stage.setX(500);
            stage.setY(200);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    void competitorMenuItemClicked(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competitor_codebook.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 420);
        stage.setTitle("Šifarnik takmičara");
        stage.setScene(scene);

        stage.setX(500);
        stage.setY(200);

        stage.show();
    }
}
