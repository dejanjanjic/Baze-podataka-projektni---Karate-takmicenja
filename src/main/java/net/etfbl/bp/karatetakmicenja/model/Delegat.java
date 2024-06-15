package net.etfbl.bp.karatetakmicenja.model;

public class Delegat {
    private Integer idDelegata;
    private String ime;
    private String prezime;

    public Delegat(int idDelegata, String ime, String prezime) {
        this.idDelegata = idDelegata;
        this.ime = ime;
        this.prezime = prezime;
    }

    public int getIdDelegata() {
        return idDelegata;
    }

    public void setIdDelegata(int idDelegata) {
        this.idDelegata = idDelegata;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
