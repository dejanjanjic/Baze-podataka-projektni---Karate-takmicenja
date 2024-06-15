package net.etfbl.bp.karatetakmicenja.gui.competitor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarDAO;
import net.etfbl.bp.karatetakmicenja.model.Takmicar;

public class CompetitorDeleteController {
    @FXML
    private Button obrisiButton;

    @FXML
    private TextField idTakmicaraTextField;

    @FXML
    void deleteCompetitor(MouseEvent event) {
        String idTakmicara = idTakmicaraTextField.getText();

        TakmicarDAO takmicarDAO = new TakmicarDAO();
        Takmicar takmicar = takmicarDAO.select(Integer.parseInt(idTakmicara));

        takmicarDAO.delete(takmicar);

        Stage stage = (Stage) obrisiButton.getScene().getWindow();
        stage.close();

    }
}
