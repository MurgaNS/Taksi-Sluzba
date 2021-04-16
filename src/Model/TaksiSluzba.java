package Model;

public class TaksiSluzba {

    protected long PIB;
    protected String naziv;
    protected String adresa;
    protected double cenaStarta;
    protected double cenaPoKilometru;

    public TaksiSluzba() {
    }

    public TaksiSluzba(long PIB, String naziv, String adresa, double cenaStarta, double cenaPoKilometru) {
        this.PIB = PIB;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStarta = cenaStarta;
        this.cenaPoKilometru = cenaPoKilometru;
    }

    public long getPIB() {
        return PIB;
    }

    public void setPIB(long PIB) {
        this.PIB = PIB;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCenaStarta() {
        return cenaStarta;
    }

    public void setCenaStarta(double cenaStarta) {
        this.cenaStarta = cenaStarta;
    }

    public double getCenaPoKilometru() {
        return cenaPoKilometru;
    }

    public void setCenaPoKilometru(double cenaPoKilometru) {
        this.cenaPoKilometru = cenaPoKilometru;
    }

    @Override
    public String toString() {
        return "TaksiSluzba{" +
                "PIB=" + PIB +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", cenaStarta=" + cenaStarta +
                ", cenaPoKilometru=" + cenaPoKilometru +
                '}';
    }

    public String stringToSave() {
        return PIB + "," + naziv + "," + adresa + "," + cenaStarta + "," + cenaPoKilometru;
    }
}