package net.etfbl.bp.karatetakmicenja.model;

public class Takmicenje {
    private Integer idTakmicenja;
    private String naziv;
    private String datumOdrzavanja;
    private String mjestoOdrzavanja;
    private Integer idDoktora;
    private Integer idDelegata;

    public Takmicenje(Integer idTakmicenja, String naziv, String datumOdrzavanja, String mjestoOdrzavanja, Integer idDoktora, Integer idDelegata) {
        this.idTakmicenja = idTakmicenja;
        this.naziv = naziv;
        this.datumOdrzavanja = datumOdrzavanja;
        this.mjestoOdrzavanja = mjestoOdrzavanja;
        this.idDoktora = idDoktora;
        this.idDelegata = idDelegata;
    }
    public Takmicenje(String naziv, String datumOdrzavanja, String mjestoOdrzavanja, Integer idDoktora, Integer idDelegata) {
        this(null, naziv, datumOdrzavanja, mjestoOdrzavanja, idDoktora, idDelegata);
    }

    public Integer getIdTakmicenja() {
        return idTakmicenja;
    }

    public void setIdTakmicenja(Integer idTakmicenja) {
        this.idTakmicenja = idTakmicenja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDatumOdrzavanja() {
        return datumOdrzavanja;
    }

    public void setDatumOdrzavanja(String datumOdrzavanja) {
        this.datumOdrzavanja = datumOdrzavanja;
    }

    public String getMjestoOdrzavanja() {
        return mjestoOdrzavanja;
    }

    public void setMjestoOdrzavanja(String mjestoOdrzavanja) {
        this.mjestoOdrzavanja = mjestoOdrzavanja;
    }

    public Integer getIdDoktora() {
        return idDoktora;
    }

    public void setIdDoktora(Integer idDoktora) {
        this.idDoktora = idDoktora;
    }

    public Integer getIdDelegata() {
        return idDelegata;
    }

    public void setIdDelegata(Integer idDelegata) {
        this.idDelegata = idDelegata;
    }
}
