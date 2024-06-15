package net.etfbl.bp.karatetakmicenja.model;

public class Takmicar {
    private Integer idTakmicara;
    private String ime;
    private String prezime;
    private String datumRodjenja;
    private Double kilaza;
    private Integer idKluba;

    public Takmicar(Integer idTakmicara, String ime, String prezime, String datumRodjenja, Double kilaza, Integer idKluba) {
        this.idTakmicara = idTakmicara;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kilaza = kilaza;
        this.idKluba = idKluba;
    }
    public Takmicar(String ime, String prezime, String datumRodjenja, double kilaza, int idKluba) {
        this(null, ime, prezime, datumRodjenja, kilaza, idKluba);
    }

    public Integer getIdTakmicara() {
        return idTakmicara;
    }

    public void setIdTakmicara(Integer idTakmicara) {
        this.idTakmicara = idTakmicara;
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

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Double getKilaza() {
        return kilaza;
    }

    public void setKilaza(Double kilaza) {
        this.kilaza = kilaza;
    }

    public Integer getIdKluba() {
        return idKluba;
    }

    public void setIdKluba(Integer idKluba) {
        this.idKluba = idKluba;
    }
}
