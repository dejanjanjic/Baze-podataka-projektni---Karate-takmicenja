package net.etfbl.bp.karatetakmicenja.model;

public class TakmicariUKategoriji {
    private Integer idTakmicenja;
    private Integer idTakmicarskeJedinice;
    private Integer idKategorije;
    private String ime;
    private String prezime;
    private String naziv;
    private Integer plasman;

    public TakmicariUKategoriji(Integer idTakmicenja, Integer idTakmicarskeJedinice, Integer idKategorije, String ime, String prezime, String naziv, Integer plasman) {
        this.idTakmicenja = idTakmicenja;
        this.idTakmicarskeJedinice = idTakmicarskeJedinice;
        this.idKategorije = idKategorije;
        this.ime = ime;
        this.prezime = prezime;
        this.naziv = naziv;
        this.plasman = plasman;
    }

    public Integer getIdTakmicenja() {
        return idTakmicenja;
    }

    public void setIdTakmicenja(Integer idTakmicenja) {
        this.idTakmicenja = idTakmicenja;
    }

    public Integer getIdTakmicarskeJedinice() {
        return idTakmicarskeJedinice;
    }

    public void setIdTakmicarskeJedinice(Integer idTakmicarskeJedinice) {
        this.idTakmicarskeJedinice = idTakmicarskeJedinice;
    }

    public Integer getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Integer idKategorije) {
        this.idKategorije = idKategorije;
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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getPlasman() {
        return plasman;
    }

    public void setPlasman(Integer plasman) {
        this.plasman = plasman;
    }
}
