package net.etfbl.bp.karatetakmicenja.gui.competition;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.dao.KategorijaDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO;
import net.etfbl.bp.karatetakmicenja.model.Kategorija;
import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.util.ArrayList;
import java.util.List;

public class AddCompetitorInCompetitionController {
    @FXML
    private ChoiceBox<String> categoriesChoiceBox;

    @FXML
    private ChoiceBox<String> competitorsChoiceBox;

    @FXML
    private Button dodajButton;

    private Takmicenje takmicenje;

    public void initialize(){
        KategorijaDAO kategorijaDAO = new KategorijaDAO();
        List<Kategorija> kategorijaList = kategorijaDAO.selectAll();
        List<String> kategorijaNazivi = new ArrayList<>();
        for (Kategorija k :
                kategorijaList) {
            kategorijaNazivi.add(k.getIdKategorije() + " " + k.getNaziv());
        }
        categoriesChoiceBox.getItems().addAll(kategorijaNazivi);

        TakmicarDAO takmicarDAO = new TakmicarDAO();
        List<Takmicar> takmicariList = takmicarDAO.selectAll();
        List<String> takmicariNazivi = new ArrayList<>();
        for (Takmicar t :
                takmicariList) {
            takmicariNazivi.add(t.getIdTakmicara() + " " + t.getIme() + " " + t.getPrezime());
        }
        competitorsChoiceBox.getItems().addAll(takmicariNazivi);
    }
    @FXML
    void addCompetitor(MouseEvent event) {
        TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO dao = new TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO();
        dao.insert(String.valueOf(takmicenje.getIdTakmicenja()), competitorsChoiceBox.getValue().split(" ")[0], categoriesChoiceBox.getValue().split(" ")[0]);
        Stage stage = (Stage) dodajButton.getScene().getWindow();
        stage.close();
    }

    public void initData(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
    }
}
