package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Doktor;
import net.etfbl.bp.karatetakmicenja.model.Takmicenje;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TakmicenjeDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM takmicenje";
    private static final String SQL_SELECT = "SELECT * FROM takmicenje where IdTakmicenja=?";
    private static final String SQL_INSERT = "INSERT INTO takmicenje (IdTakmicenja, Naziv, DatumOdrzavanja, MjestoOdrzavanja, IdDoktora, IdDelegata) VALUES (null, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE takmicenje SET Naziv=?, DatumOdrzavanja=?, MjestoOdrzavanja=?, IdDoktora=?, IdDelegata=? WHERE IdTakmicenja=?";
    private static final String SQL_UPDATE_NAZIV = "UPDATE takmicenje SET Naziv=? WHERE IdTakmicenja=?";
    private static final String SQL_DELETE = "DELETE FROM takmicenje WHERE IdTakmicenja=?";

    public List<Takmicenje> selectAll() {
        List<Takmicenje> retVal = new ArrayList<Takmicenje>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                retVal.add(new Takmicenje(rs.getInt("IdTakmicenja"), rs.getString("Naziv"),
                        rs.getString("DatumOdrzavanja"), rs.getString("MjestoOdrzavanja"),
                        rs.getInt("IdDoktora"), rs.getInt("IdDelegata")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }

        return retVal;
    }

    public Takmicenje select(int id) {
        Takmicenje retVal = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new Takmicenje(rs.getInt("IdTakmicenja"), rs.getString("Naziv"),
                        rs.getString("DatumOdrzavanja"), rs.getString("MjestoOdrzavanja"),
                        rs.getInt("IdDoktora"), rs.getInt("IdDelegata"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }

    public int insert(Takmicenje takmicenje) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = {takmicenje.getNaziv(), takmicenje.getDatumOdrzavanja(), takmicenje.getMjestoOdrzavanja(), takmicenje.getIdDoktora(), takmicenje.getIdDelegata() };
            ps = DBUtil.prepareStatement(c, SQL_INSERT, true, values);
            retVal = ps.executeUpdate();
            if (retVal != 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next())
                    takmicenje.setIdTakmicenja(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }

    public int update(Takmicenje takmicenje) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = { takmicenje.getNaziv(), takmicenje.getDatumOdrzavanja(), takmicenje.getMjestoOdrzavanja(),
                    takmicenje.getIdDoktora(), takmicenje.getIdDelegata(), takmicenje.getIdTakmicenja() };
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public int delete(Takmicenje takmicenje) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object values[] = { takmicenje.getIdTakmicenja() };
            ps = DBUtil.prepareStatement(c, SQL_DELETE, false, values);
            retVal = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }
}
