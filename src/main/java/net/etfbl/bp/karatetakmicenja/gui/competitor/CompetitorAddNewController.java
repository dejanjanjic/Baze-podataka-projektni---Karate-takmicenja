package net.etfbl.bp.karatetakmicenja.gui.competitor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.dao.KlubDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarDAO;
import net.etfbl.bp.karatetakmicenja.model.Klub;
import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CompetitorAddNewController {
    @FXML
    private DatePicker datumDatePicker;

    @FXML
    private Button dodajButton;

    @FXML
    private TextField imeTextField;

    @FXML
    private TextField kilazaTextField;

    @FXML
    private ChoiceBox<String> klubChoiceBox;

    @FXML
    private TextField prezimeTextField;

    public void initialize(){
        KlubDAO klubDAO = new KlubDAO();
        List<Klub> klubovi = klubDAO.selectAll();
        List<String> kluboviNazivi = new ArrayList<>();
        for (Klub k :
                klubovi) {
            kluboviNazivi.add(k.getIdKluba() + " " + k.getNaziv());
        }

        klubChoiceBox.getItems().addAll(kluboviNazivi);
    }

    @FXML
    void addCompetitor(MouseEvent event) {
        String ime, prezime, datumRodjenja;
        double kilaza;
        int idKluba;

        LocalDate localDate = datumDatePicker.getValue();

        ime = imeTextField.getText();
        prezime = prezimeTextField.getText();
        datumRodjenja = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        KlubDAO klubDAO = new KlubDAO();

        kilaza = Double.parseDouble(kilazaTextField.getText());

        String fieldKlub = klubChoiceBox.getValue();
        String idFieldKlub = fieldKlub.split(" ")[0];
        idKluba = klubDAO.select(Integer.parseInt(idFieldKlub)).getIdKluba();

        Takmicar takmicar = new Takmicar(ime, prezime, datumRodjenja, kilaza, idKluba);
        TakmicarDAO takmicarDAO = new TakmicarDAO();
        takmicarDAO.insert(takmicar);

        Stage stage = (Stage) dodajButton.getScene().getWindow();
        stage.close();
    }
}
