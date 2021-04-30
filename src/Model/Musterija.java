package Model;


public class Musterija extends Korisnik {

    public Musterija() {
    }

    protected boolean narucivanjePutemTelefona() {
        return false;
    }
    protected boolean narucivanjePutemAplikacije() {
        return false;
    }
    protected void prikazIstorijeVoznji() {
    }

    public Musterija(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
    }

    public String korisnikUString(){
        return "musterija,"+super.korisnikUString();
    }
}