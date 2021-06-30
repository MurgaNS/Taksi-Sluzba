package Model;

public class Musterija extends Korisnik {
    private long id;

    public Musterija(long id, long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, Boolean obrisan) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Musterija{" +
               "id=" + id +
               ", JMBG=" + JMBG +
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
    public static Musterija musterijaDTO(String[] lineParts){
        long id = Long.parseLong(lineParts[1]);
        long jmbg = Long.parseLong(lineParts[2]);
        String korisnickoIme = lineParts[3];
        String lozinka = lineParts[4];
        String ime = lineParts[5];
        String prezime = lineParts[6];
        String adresa = lineParts[7];
        Pol pol = ucitajPol(lineParts[8]);
        String brojTelefona = lineParts[9];
        boolean obrisan = Boolean.parseBoolean(lineParts[10]);
        return new Musterija(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
    }
    public long getId() {
        return id;
    }

    public String korisnikUString() {
        return "musterija," + id + "," + super.korisnikUString();
    }


    public void setId(long id) {
        this.id = id;
    }
}