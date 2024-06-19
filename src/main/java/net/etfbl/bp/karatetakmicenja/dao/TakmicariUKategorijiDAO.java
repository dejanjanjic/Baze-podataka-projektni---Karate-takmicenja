package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Kategorija;
import net.etfbl.bp.karatetakmicenja.model.TakmicariUKategoriji;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TakmicariUKategorijiDAO {
    private static final String SQL_SELECT_ALL_FROM_CATEGORY = "SELECT * FROM takmicari_u_kategoriji " +
            "WHERE IdTakmicenja=? AND IdKategorije=?";

    public List<TakmicariUKategoriji> selectAllFromCategory(Takmicenje t, Kategorija k){
        List<TakmicariUKategoriji> retVal = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT_ALL_FROM_CATEGORY);
            ps.setInt(1, t.getIdTakmicenja());
            ps.setInt(2, k.getIdKategorije());
            rs = ps.executeQuery();

            while (rs.next()) {
                retVal.add(new TakmicariUKategoriji(rs.getInt("IdTakmicenja"), rs.getInt("IdTakmicarskeJedinice"),
                        rs.getInt("IdKategorije"), rs.getString("Ime"), rs.getString("Prezime"),
                        rs.getString("Naziv"), rs.getInt("Plasman")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }
}
