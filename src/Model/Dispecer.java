package Model;

public class Dispecer extends Korisnik {
    private double plata;
    private String brojTelefonskeLinije;
    private OdeljenjeRada odeljenjeRada;

    public enum OdeljenjeRada {
        PRIJEM_VOZNJE,
        REKLAMACIJE
    }

    public Dispecer(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, String brojTelefonskeLinije, OdeljenjeRada odeljenjeRada) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.plata = plata;
        this.brojTelefonskeLinije = brojTelefonskeLinije;
        this.odeljenjeRada = odeljenjeRada;
    }

    public String korisnikUString() {
        return "dispecer," + super.korisnikUString() + "," + plata + "," + brojTelefonskeLinije + "," + odeljenjeRada;
    }

    @Override
    public String toString() {
        return "Dispecer{" +
               "JMBG=" + JMBG +
               ", korisnickoIme='" + korisnickoIme + '\'' +
               ", lozinka='" + lozinka + '\'' +
               ", ime='" + ime + '\'' +
               ", prezime='" + prezime + '\'' +
               ", adresa='" + adresa + '\'' +
               ", pol=" + pol +
               ", brojTelefona='" + brojTelefona + '\'' +
               ", obrisan=" + obrisan +
               ", plata=" + plata +
               ", brojTelefonskeLinije='" + brojTelefonskeLinije + '\'' +
               ", odeljenjeRada=" + odeljenjeRada +
               '}';
    }

    public static OdeljenjeRada ucitajOdeljenjeRada(String odeljenjeRada) {
        if (odeljenjeRada.trim().equals("PRIJEM_VOZNJE")) {
            return Dispecer.OdeljenjeRada.PRIJEM_VOZNJE;
        } else if (odeljenjeRada.trim().equals("REKLAMACIJE")) {
            return Dispecer.OdeljenjeRada.REKLAMACIJE;
        } else {
            return null;
        }
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public String getBrojTelefonskeLinije() {
        return brojTelefonskeLinije;
    }

    public void setBrojTelefonskeLinije(String brojTelefonskeLinije) {
        this.brojTelefonskeLinije = brojTelefonskeLinije;
    }

    public OdeljenjeRada getOdeljenjeRada() {
        return odeljenjeRada;
    }

    public void setOdeljenjeRada(OdeljenjeRada odeljenjeRada) {
        this.odeljenjeRada = odeljenjeRada;
    }

}