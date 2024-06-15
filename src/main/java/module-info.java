module net.etfbl.bp.karatetakmicenja {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens net.etfbl.bp.karatetakmicenja to javafx.fxml;
    exports net.etfbl.bp.karatetakmicenja;
    exports net.etfbl.bp.karatetakmicenja.gui;
    opens net.etfbl.bp.karatetakmicenja.gui to javafx.fxml;
    opens net.etfbl.bp.karatetakmicenja.model to javafx.base;
    exports net.etfbl.bp.karatetakmicenja.gui.competition;
    opens net.etfbl.bp.karatetakmicenja.gui.competition to javafx.fxml;
}