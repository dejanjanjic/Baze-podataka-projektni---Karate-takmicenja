package net.etfbl.bp.karatetakmicenja.model;

public class Doktor {
    private Integer idDoktora;
    private String ime;
    private String prezime;

    public Doktor(Integer idDoktora, String ime, String prezime) {
        this.idDoktora = idDoktora;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getIdDoktora() {
        return idDoktora;
    }

    public void setIdDoktora(Integer idDoktora) {
        this.idDoktora = idDoktora;
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
