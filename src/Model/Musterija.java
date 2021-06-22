package Model;

public class Musterija extends Korisnik {

    public Musterija(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, Boolean obrisan) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
    }

    @Override
    public String toString() {
        return "Musterija{" +
               "JMBG=" + JMBG +
               ", korisnickoIme='" + korisnickoIme + '\'' +
               ", lozinka='" + lozinka + '\'' +
               ", ime='" + ime + '\'' +
               ", prezime='" + prezime + '\'' +
               ", adresa='" + adresa + '\'' +
               ", pol=" + pol +
               ", brojTelefona='" + brojTelefona + '\'' +
               ", obrisan=" + obrisan +
               '}';
    }
}