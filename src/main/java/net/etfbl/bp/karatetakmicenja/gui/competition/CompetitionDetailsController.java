package net.etfbl.bp.karatetakmicenja.gui.competition;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import net.etfbl.bp.karatetakmicenja.Application;
import net.etfbl.bp.karatetakmicenja.dao.KategorijaDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicariUKategorijiDAO;
import net.etfbl.bp.karatetakmicenja.dao.TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO;
import net.etfbl.bp.karatetakmicenja.model.Kategorija;
import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.model.TakmicariUKategoriji;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class CompetitionDetailsController {
    @FXML
    private Label competitionNameLabel;

    @FXML
    private Accordion kategorijeAccordion;
    @FXML
    private TextField plasmanTextField;

    private Takmicenje takmicenje;

    public void initData(Takmicenje takmicenje) {
        this.takmicenje = takmicenje;
        competitionNameLabel.setText(takmicenje.getNaziv());

        KategorijaDAO kategorijaDAO = new KategorijaDAO();
        List<Kategorija> kategorijaList = kategorijaDAO.selectAll();
        for (Kategorija k :
                kategorijaList) {
            ListView<String> listView = new ListView<>();
            loadCategoryParticipants(listView, takmicenje, k);
            kategorijeAccordion.getPanes().add(new TitledPane(k.getIdKategorije() + " " + k.getNaziv(), listView));
        }
    }

    private void loadCategoryParticipants(ListView<String> listView, Takmicenje takmicenje, Kategorija kategorija) {
        TakmicariUKategorijiDAO takmicariUKategorijiDAO = new TakmicariUKategorijiDAO();
        List<TakmicariUKategoriji> takmicari = takmicariUKategorijiDAO.selectAllFromCategory(takmicenje, kategorija);
        for (TakmicariUKategoriji takmicar : takmicari) {
            listView.getItems().add(takmicar.getIdTakmicarskeJedinice() + " " + takmicar.getIme() + " " + takmicar.getPrezime() + " " + takmicar.getNaziv() + " PLASMAN: " + (takmicar.getPlasman()==null?".":takmicar.getPlasman()));
        }
        sortListViewByPlasman(listView);
    }

    @FXML
    void addCompetitor(MouseEvent event) {
        openAddCompetitorWindow();
    }

    private void openAddCompetitorWindow() {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("add_competitor_in_competition.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 364, 329);

            // Dobijemo kontroler nove scene i proslijedimo mu podatke
            AddCompetitorInCompetitionController controller = fxmlLoader.getController();
            controller.initData(takmicenje);

            stage.setTitle("Dodaj takmičara");
            stage.setScene(scene);
            stage.setX(500);
            stage.setY(200);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void evidentirajPlasmanButtonClicked(MouseEvent event) {
        for (TitledPane titledPane : kategorijeAccordion.getPanes()) {
            if (titledPane.getContent() instanceof ListView) {
                ListView<String> listView = (ListView<String>) titledPane.getContent();
                String selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    int idTakmicara = Integer.parseInt(selectedItem.split(" ")[0]);
                    int plasman = Integer.parseInt(plasmanTextField.getText());
                    int kategorija = Integer.parseInt(titledPane.getText().split(" ")[0]);
                    int takmicenjeId = takmicenje.getIdTakmicenja();
                    new TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO().updatePlasman(takmicenjeId, idTakmicara, kategorija, plasman);
                    updatePlasman(titledPane, takmicenje, kategorija);
                }
            }
        }
    }

    private void updatePlasman(TitledPane titledPane, Takmicenje takmicenje, int kategorijaId) {
        if (titledPane.getContent() instanceof ListView) {
            ListView<String> listView = (ListView<String>) titledPane.getContent();
            ObservableList<String> items = listView.getItems();

            items.clear();
            TakmicariUKategorijiDAO takmicariUKategorijiDAO = new TakmicariUKategorijiDAO();
            Kategorija kategorija = new KategorijaDAO().select(kategorijaId);
            List<TakmicariUKategoriji> takmicari = takmicariUKategorijiDAO.selectAllFromCategory(takmicenje, kategorija);
            for (TakmicariUKategoriji takmicar : takmicari) {
                items.add(takmicar.getIdTakmicarskeJedinice() + " " + takmicar.getIme() + " " + takmicar.getPrezime() + " " + takmicar.getNaziv() + " PLASMAN: " + (takmicar.getPlasman()==null?".":takmicar.getPlasman()));
            }
            sortListViewByPlasman(listView);
        }
    }

    private void sortListViewByPlasman(ListView<String> listView) {
        ObservableList<String> items = listView.getItems();
        FXCollections.sort(items, (o1, o2) -> {
            Integer plasman1 = Integer.valueOf(o1.split(" ")[o1.split(" ").length - 1]);
            Integer plasman2 = Integer.valueOf(o2.split(" ")[o2.split(" ").length - 1]);

            // Sort 0s to the end
            if (plasman1 == 0 && plasman2 == 0) {
                return 0;
            } else if (plasman1 == 0) {
                return 1;
            } else if (plasman2 == 0) {
                return -1;
            } else {
                return plasman1.compareTo(plasman2);
            }
        });
    }

    @FXML
    void refresh(MouseEvent event) {
        kategorijeAccordion.getPanes().clear();
        initData(takmicenje);
    }

}