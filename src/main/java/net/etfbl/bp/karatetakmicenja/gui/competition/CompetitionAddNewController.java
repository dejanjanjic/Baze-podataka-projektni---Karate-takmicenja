package net.etfbl.bp.karatetakmicenja.gui.competition;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.dao.DelegatDAO;
import net.etfbl.bp.karatetakmicenja.dao.DoktorDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicenjeDAO;
import net.etfbl.bp.karatetakmicenja.model.Delegat;
import net.etfbl.bp.karatetakmicenja.model.Doktor;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CompetitionAddNewController {
    @FXML
    private DatePicker datumDatePicker;

    @FXML
    private ChoiceBox<String> delegatChoiceBox;


    @FXML
    private ChoiceBox<String> doktorChoiceBox;

    @FXML
    private TextField mjestoTextField;

    @FXML
    private TextField nazivTextField;

    @FXML
    private Button dodajButton;

    public void initialize() {
        DoktorDAO doktorDAO = new DoktorDAO();
        List<Doktor> doktori = doktorDAO.selectAll();
        List<String> doktoriNazivi = new ArrayList<>();
        for (Doktor d :
                doktori) {
            doktoriNazivi.add(d.getIdDoktora() + " " + d.getIme() + " " + d.getPrezime());
        }

        doktorChoiceBox.getItems().addAll(doktoriNazivi);

        DelegatDAO delegatDAO = new DelegatDAO();
        List<Delegat> delegati = delegatDAO.selectAll();
        List<String> delegatiNazivi = new ArrayList<>();
        for (Delegat d :
                delegati) {
            delegatiNazivi.add(d.getIdDelegata() + " " + d.getIme() + " " + d.getPrezime());
        }

        delegatChoiceBox.getItems().addAll(delegatiNazivi);
    }

    @FXML
    void addCompetition(MouseEvent event) {
        String naziv, mjestoOdrzavanja, datumOdrzavanja;
        int idDoktora, idDelegata;

        LocalDate localDate = datumDatePicker.getValue();

        naziv = nazivTextField.getText();
        mjestoOdrzavanja = mjestoTextField.getText();
        datumOdrzavanja = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        DoktorDAO doktorDAO = new DoktorDAO();

        String fieldDoktor = doktorChoiceBox.getValue();
        String idFieldDoktor = fieldDoktor.split(" ")[0];
        idDoktora = doktorDAO.select(Integer.parseInt(idFieldDoktor)).getIdDoktora();

        DelegatDAO delegatDAO = new DelegatDAO();

        String fieldDelegat = delegatChoiceBox.getValue();
        String idFieldDelegat = fieldDelegat.split(" ")[0];
        idDelegata = delegatDAO.select(Integer.parseInt(idFieldDelegat)).getIdDelegata();

        Takmicenje takmicenje = new Takmicenje(naziv, datumOdrzavanja, mjestoOdrzavanja, idDoktora, idDelegata);
        TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
        takmicenjeDAO.insert(takmicenje);

        Stage stage = (Stage) dodajButton.getScene().getWindow();
        stage.close();
    }
}
