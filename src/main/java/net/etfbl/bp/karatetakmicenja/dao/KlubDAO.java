package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Klub;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlubDAO {
    private static final String SQL_SELECT_ALL = "SELECT * from klub";
    private static final String SQL_SELECT = "SELECT * FROM klub where IdKluba=?";

    public List<Klub> selectAll(){
        List<Klub> retVal = new ArrayList<>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                retVal.add(new Klub(rs.getInt("IdKluba"), rs.getString("Naziv"), rs.getString("Sjediste")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }

        return retVal;
    }
    public Klub select(int id) {
        Klub retVal = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new Klub(rs.getInt("IdKluba"), rs.getString("Naziv"),
                        rs.getString("Sjediste"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }
}
