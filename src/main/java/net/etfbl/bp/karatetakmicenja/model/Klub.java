package net.etfbl.bp.karatetakmicenja.model;

public class Klub {
    private int idKluba;
    private String naziv;
    private String sjediste;

    public Klub(int idKluba, String naziv, String sjediste) {
        this.idKluba = idKluba;
        this.naziv = naziv;
        this.sjediste = sjediste;
    }

    public int getIdKluba() {
        return idKluba;
    }

    public void setIdKluba(int idKluba) {
        this.idKluba = idKluba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSjediste() {
        return sjediste;
    }

    public void setSjediste(String sjediste) {
        this.sjediste = sjediste;
    }
}
