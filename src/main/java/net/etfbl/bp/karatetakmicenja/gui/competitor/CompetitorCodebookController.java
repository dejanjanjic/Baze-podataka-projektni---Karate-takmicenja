package net.etfbl.bp.karatetakmicenja.gui.competitor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import net.etfbl.bp.karatetakmicenja.Application;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicenjeDAO;
import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.io.IOException;
import java.util.List;

public class CompetitorCodebookController {

    @FXML
    private TableView<Takmicar> competitionTable;

    @FXML
    private TableColumn<Takmicar, Integer> idTakmicaraColumn;
    @FXML
    private TableColumn<Takmicar, String> imeColumn;
    @FXML
    private TableColumn<Takmicar, String> prezimeColumn;
    @FXML
    private TableColumn<Takmicar, String> datumRodjenjaColumn;
    @FXML
    private TableColumn<Takmicar, Double> kilazaColumn;
    @FXML
    private TableColumn<Takmicar, Integer> idKlubaColumn;



    @FXML
    private TextField searchTextField;

    public void initialize() {
        idTakmicaraColumn.setCellValueFactory(new PropertyValueFactory<>("idTakmicara"));
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));
        kilazaColumn.setCellValueFactory(new PropertyValueFactory<>("kilaza"));
        idKlubaColumn.setCellValueFactory(new PropertyValueFactory<>("idKluba"));

        competitionTable.setEditable(true);

        // Postavljanje cell factory za omogućavanje uređivanja (TextFieldTableCell)
        imeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prezimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        datumRodjenjaColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        kilazaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        idKlubaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        imeColumn.setOnEditCommit(event -> {
            Takmicar takmicar = event.getRowValue();
            takmicar.setIme(event.getNewValue());
            TakmicarDAO takmicarDAO = new TakmicarDAO();
            takmicarDAO.update(takmicar);
        });
        prezimeColumn.setOnEditCommit(event -> {
            Takmicar takmicar = event.getRowValue();
            takmicar.setPrezime(event.getNewValue());
            TakmicarDAO takmicarDAO = new TakmicarDAO();
            takmicarDAO.update(takmicar);
        });
        datumRodjenjaColumn.setOnEditCommit(event -> {
            Takmicar takmicar = event.getRowValue();
            takmicar.setDatumRodjenja(event.getNewValue());
            TakmicarDAO takmicarDAO = new TakmicarDAO();
            takmicarDAO.update(takmicar);
        });
        kilazaColumn.setOnEditCommit(event -> {
            Takmicar takmicar = event.getRowValue();
            takmicar.setKilaza(event.getNewValue());
            TakmicarDAO takmicarDAO = new TakmicarDAO();
            takmicarDAO.update(takmicar);
        });
        idKlubaColumn.setOnEditCommit(event -> {
            Takmicar takmicar = event.getRowValue();
            takmicar.setIdKluba(event.getNewValue());
            TakmicarDAO takmicarDAO = new TakmicarDAO();
            takmicarDAO.update(takmicar);
        });


        showAll();
    }

    @FXML
    void addNewCompetitor(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competitor_add_new.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 364, 318);
        stage.setTitle("Dodaj takmičenje");
        stage.setScene(scene);


        stage.show();
    }

    @FXML
    void deleteCompetitor(MouseEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("competitor_delete.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 364, 363);
        stage.setTitle("Obriši takmičara");
        stage.setScene(scene);


        stage.show();
    }

    @FXML
    void searchOnClick(MouseEvent event) {
        String id = searchTextField.getText();

        TakmicarDAO takmicarDAO = new TakmicarDAO();
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
        Takmicar takmicar = takmicarDAO.select(Integer.parseInt(id));

        competitionTable.getItems().clear();
        competitionTable.getItems().add(takmicar);
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
        TakmicarDAO takmicarDAO = new TakmicarDAO();
        List<Takmicar> listOfCompetitors =  takmicarDAO.selectAll();

        competitionTable.getItems().addAll(listOfCompetitors);
    }

}
