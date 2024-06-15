package net.etfbl.bp.karatetakmicenja.gui.competition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import net.etfbl.bp.karatetakmicenja.Application;
import net.etfbl.bp.karatetakmicenja.dao.DoktorDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicenjeDAO;
import net.etfbl.bp.karatetakmicenja.model.Doktor;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.io.IOException;
import java.util.List;

public class CompetitionCodebookController {

    @FXML
    private TableView<Takmicenje> competitionTable;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<Takmicenje, String> datumOdrzavanjaColumn;

    @FXML
    private TableColumn<Takmicenje, Integer> idDelegataColumn;

    @FXML
    private TableColumn<Takmicenje, Integer> idDoktoraColumn;

    @FXML
    private TableColumn<Takmicenje, Integer> idTakmicenjaColumn;

    @FXML
    private TableColumn<Takmicenje, String> mjestoOdrzavanjaColumn;

    @FXML
    private TableColumn<Takmicenje, String> nazivColumn;
    public void initialize() {
        idTakmicenjaColumn.setCellValueFactory(new PropertyValueFactory<>("idTakmicenja"));
        nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        datumOdrzavanjaColumn.setCellValueFactory(new PropertyValueFactory<>("datumOdrzavanja"));
        mjestoOdrzavanjaColumn.setCellValueFactory(new PropertyValueFactory<>("mjestoOdrzavanja"));
        idDoktoraColumn.setCellValueFactory(new PropertyValueFactory<>("idDoktora"));
        idDelegataColumn.setCellValueFactory(new PropertyValueFactory<>("idDelegata"));
        competitionTable.setEditable(true);

        // Postavljanje cell factory za omogućavanje uređivanja (TextFieldTableCell)
        nazivColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        datumOdrzavanjaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        mjestoOdrzavanjaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idDoktoraColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idDelegataColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        nazivColumn.setOnEditCommit(event -> {
            Takmicenje takmicenje = event.getRowValue();
            takmicenje.setNaziv(event.getNewValue());
            TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
            takmicenjeDAO.update(takmicenje);
        });
        datumOdrzavanjaColumn.setOnEditCommit(event -> {
            Takmicenje takmicenje = event.getRowValue();
            takmicenje.setDatumOdrzavanja(event.getNewValue());
            TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
            takmicenjeDAO.update(takmicenje);
        });
        mjestoOdrzavanjaColumn.setOnEditCommit(event -> {
            Takmicenje takmicenje = event.getRowValue();
            takmicenje.setMjestoOdrzavanja(event.getNewValue());
            TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
            takmicenjeDAO.update(takmicenje);
        });
        idDoktoraColumn.setOnEditCommit(event -> {
            Takmicenje takmicenje = event.getRowValue();
            takmicenje.setIdDoktora(event.getNewValue());
            TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
            takmicenjeDAO.update(takmicenje);
        });
        idDelegataColumn.setOnEditCommit(event -> {
            Takmicenje takmicenje = event.getRowValue();
            takmicenje.setIdDelegata(event.getNewValue());
            TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
            takmicenjeDAO.update(takmicenje);
        });


        showAll();
    }


    @FXML
    void searchOnClick(MouseEvent event) {
        String id = searchTextField.getText();

        TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
        try
        {
            int idInt = Integer.parseInt(id);
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Greška");
            alert.setHeaderText(null);
            alert.setContentText("ID mora biti broj");
            alert.showAndWait();
            return;
        }
        Takmicenje takmicenje = takmicenjeDAO.select(Integer.parseInt(id));

        competitionTable.getItems().clear();
        competitionTable.getItems().add(takmicenje);
    }
    @FXML
    void searchOnEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            searchOnClick(null);
        }
    }

    @FXML
    void showAllButtonClicked(MouseEvent event) {
        showAll();
    }

    void showAll(){
        competitionTable.getItems().clear();
        TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
        List<Takmicenje> listOfCompetitions =  takmicenjeDAO.selectAll();

        competitionTable.getItems().addAll(listOfCompetitions);
    }

    @FXML
    void addNewCompetition(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competition_add_new.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 364, 318);
        stage.setTitle("Dodaj takmičenje");
        stage.setScene(scene);


        stage.show();
    }

    @FXML
    void deleteCompetition(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competition_delete.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 364, 363);
        stage.setTitle("Obriši takmičenje");
        stage.setScene(scene);


        stage.show();
    }
}