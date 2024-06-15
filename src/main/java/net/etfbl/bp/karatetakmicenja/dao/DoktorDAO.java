package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Doktor;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoktorDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM doktor";
    private static final String SQL_SELECT = "SELECT * FROM doktor where IdDoktora=?";

    public List<Doktor> selectAll() {
        List<Doktor> retVal = new ArrayList<Doktor>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                retVal.add(new Doktor(rs.getInt("IdDoktora"), rs.getString("Ime"), rs.getString("Prezime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }

        return retVal;
    }

    public Doktor select(int id) {
        Doktor retVal = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new Doktor(rs.getInt("IdDoktora"), rs.getString("Ime"),
                        rs.getString("Prezime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }
}
