package net.etfbl.bp.karatetakmicenja.model;

public class TakmicarskaJedinicaNaTakmicenjuUKategoriji {
    private Integer idTakmicenja;
    private Integer idTakmicarskeJedinice;
    private Integer idKategorije;
    private Integer plasman;

    public TakmicarskaJedinicaNaTakmicenjuUKategoriji(Integer idTakmicenja, Integer idTakmicarskeJedinice, Integer idKategorije, Integer plasman) {
        this.idTakmicenja = idTakmicenja;
        this.idTakmicarskeJedinice = idTakmicarskeJedinice;
        this.idKategorije = idKategorije;
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

    public Integer getPlasman() {
        return plasman;
    }

    public void setPlasman(Integer plasman) {
        this.plasman = plasman;
    }
}
