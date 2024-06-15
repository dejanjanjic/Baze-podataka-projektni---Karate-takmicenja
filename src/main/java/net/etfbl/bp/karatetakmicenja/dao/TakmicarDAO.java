package net.etfbl.bp.karatetakmicenja.dao;

import net.etfbl.bp.karatetakmicenja.model.Takmicar;
import net.etfbl.bp.karatetakmicenja.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TakmicarDAO {
    private static final String SQL_SELECT_ALL = "SELECT * FROM takmicar";
    private static final String SQL_SELECT = "SELECT * FROM takmicar where IdTakmicarskeJedinice=?";
    private static final String SQL_INSERT = "INSERT INTO takmicar (IdTakmicarskeJedinice, Ime, Prezime, DatumRodjenja, Kilaza, IdKluba) VALUES (null, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE takmicar SET Ime=?, Prezime=?, DatumRodjenja=?, Kilaza=?, IdKluba=? WHERE IdTakmicarskeJedinice=?";
    private static final String SQL_DELETE = "DELETE FROM takmicar WHERE IdTakmicarskeJedinice=?";
    private static final String SQL_CALL_DODAJ_TAKMICARA = "CALL dodaj_takmicara(?, ?, ?, ?, ?)";

    public List<Takmicar> selectAll() {
        List<Takmicar> retVal = new ArrayList<Takmicar>();
        Connection c = null;
        Statement s = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            s = c.createStatement();
            rs = s.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                retVal.add(new Takmicar(rs.getInt("IdTakmicarskeJedinice"), rs.getString("Ime"),
                        rs.getString("Prezime"), rs.getString("DatumRodjenja"),
                        rs.getDouble("Kilaza"), rs.getInt("IdKluba")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, s, c);
        }

        return retVal;
    }

    public Takmicar select(int id) {
        Takmicar retVal = null;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = DBUtil.getConnection();
            ps = c.prepareStatement(SQL_SELECT);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                retVal = new Takmicar(rs.getInt("IdTakmicarskeJedinice"), rs.getString("Ime"),
                        rs.getString("Prezime"), rs.getString("DatumRodjenja"),
                        rs.getDouble("Kilaza"), rs.getInt("IdKluba"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, c);
        }

        return retVal;
    }

    public int insert(Takmicar takmicar) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;


        try {
            c = DBUtil.getConnection();
            Object[] values = {takmicar.getIme(), takmicar.getPrezime(), takmicar.getDatumRodjenja(),
                    takmicar.getKilaza(), takmicar.getIdKluba() };
            ps = DBUtil.prepareStatement(c, SQL_CALL_DODAJ_TAKMICARA, false, values);
            retVal = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }

        return retVal;
    }

    public int update(Takmicar takmicar) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = { takmicar.getIme(), takmicar.getPrezime(), takmicar.getDatumRodjenja(),
                    takmicar.getKilaza(), takmicar.getIdKluba(), takmicar.getIdTakmicara() };
            ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
            retVal = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, c);
        }
        return retVal;
    }

    public int delete(Takmicar takmicar) {
        int retVal = 0;
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = DBUtil.getConnection();
            Object[] values = { takmicar.getIdTakmicara() };
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
