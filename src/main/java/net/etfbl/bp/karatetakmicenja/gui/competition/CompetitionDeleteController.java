package net.etfbl.bp.karatetakmicenja.gui.competition;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.dao.TakmicenjeDAO;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

public class CompetitionDeleteController {
    @FXML
    private Button obrisiButton;

    @FXML
    private TextField idTakmicenjaTextField;

    @FXML
    void deleteCompetition(MouseEvent event) {
        String idTakmicenja = idTakmicenjaTextField.getText();

        TakmicenjeDAO takmicenjeDAO = new TakmicenjeDAO();
        Takmicenje takmicenje = takmicenjeDAO.select(Integer.parseInt(idTakmicenja));

        if(takmicenjeDAO.delete(takmicenje) == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("GRESKA");
            alert.setHeaderText("Ne smijete obrisati takmicenje koje vec ima takmicare, prvo obrisite takmicare iz takmicenja");
            alert.setContentText("");
            alert.showAndWait();
        }

        Stage stage = (Stage) obrisiButton.getScene().getWindow();
        stage.close();

    }
}
