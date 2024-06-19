package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TakmicarskaJedinicaNaTakmicenjuUKategorijiDAO {
    private static final String SQL_UPDATE_PLASMAN = "UPDATE takmicarska_jedinica_na_takmicenju_u_kategoriji" +
            " SET Plasman=? WHERE IdTakmicenja=? AND IdTakmicarskeJedinice=? AND IdKategorije=?";

    public int insert(String idTakmicenja, String idTakmicarskeJedinice, String idKategorije) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;


        try {
            c = DBUtil.getConnection();
            Object[] values = {idTakmicenja, idTakmicarskeJedinice, idKategorije };
            String SQL_CALL_DODAJ_TAKMICARA_NA_TAKMICENJE_U_KATEGORIJU = "CALL dodaj_takmicara_na_takmicenje_u_kategoriju(?,?,?)";
            ps = DBUtil.prepareStatement(c, SQL_CALL_DODAJ_TAKMICARA_NA_TAKMICENJE_U_KATEGORIJU, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }

        return retVal;
    }

    public int updatePlasman(int takmicenjeId, int idTakmicara, int kategorija, int plasman) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = { plasman, takmicenjeId, idTakmicara, kategorija};
            ps = DBUtil.prepareStatement(c, SQL_UPDATE_PLASMAN, false, values);
            retVal = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }
}
