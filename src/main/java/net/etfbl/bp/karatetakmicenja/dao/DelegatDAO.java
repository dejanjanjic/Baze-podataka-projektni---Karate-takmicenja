package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Delegat;
import net.etfbl.bp.karatetakmicenja.model.Doktor;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DelegatDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM delegat";
    private static final String SQL_SELECT = "SELECT * FROM delegat where IdDelegata=?";

    public List<Delegat> selectAll() {
        List<Delegat> retVal = new ArrayList<>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                retVal.add(new Delegat(rs.getInt("IdDelegata"), rs.getString("Ime"), rs.getString("Prezime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }

        return retVal;
    }

    public Delegat select(int id) {
        Delegat retVal = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new Delegat(rs.getInt("IdDelegata"), rs.getString("Ime"),
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
