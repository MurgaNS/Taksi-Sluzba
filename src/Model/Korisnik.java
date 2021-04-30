package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Korisnik {

    protected long JMBG;
    protected String korisnickoIme;
    protected String lozinka;
    protected String ime;
    protected String prezime;
    protected String adresa;
    protected String pol;
    protected String brojTelefona;

    public Korisnik() {
    }

    public Korisnik(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona) {
        this.JMBG = JMBG;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.pol = pol;
        this.brojTelefona = brojTelefona;
    }
    public static Korisnik nadjiKorisnikaPrekoJMBG(long JMBG){
        List<Korisnik> korisnici = ucitajSveKorisnike();
        for(Korisnik korisnik : korisnici){
            if(korisnik.getJMBG() == JMBG){
                return korisnik;
            }
        }
        return null;
    }
    public static List<Korisnik> ucitajSveKorisnike(){
        List<Korisnik> sviKorisnici = new ArrayList<>();
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Korisnik korisnik = null;
                String[] lineParts = line.split(",");
//                System.out.println(lineParts.toString());
                    switch (lineParts[0]) {
                        case "musterija":
                            korisnik = new Musterija(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8]);
                            break;

                        case "vozac":
                            korisnik = new Vozac(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8], Double.parseDouble(lineParts[9]), Integer.parseInt(lineParts[10]));
                            break;

                        case "dispecer":
                            korisnik = new Dispecer(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], lineParts[7], lineParts[8], Double.parseDouble(lineParts[9]), lineParts[10], lineParts[11]);
                            break;

                    }
                sviKorisnici.add(korisnik);
            }

            bufferedReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        } catch (Exception e) {
            System.out.println("Niste uneli tacne podatke, molimo Vas pokusajte ponovo.");
        }
        return sviKorisnici;

    }
    
    public static void upisiSveKorisnike(List<Korisnik> korisnici){
        File file = new File("src\\Data\\korisnici.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for(Korisnik korisnik : korisnici) {
                writer.write(korisnik.korisnikUString()+"\n");
            }
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException exception){
            System.out.println("Nepostojeći fajl");
        }
    }


    public long getJMBG() {
        return JMBG;
    }

    public void setJMBG(long JMBG) {
        this.JMBG = JMBG;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }


    public String korisnikUString() {
        return JMBG +
                "," + korisnickoIme +
                "," + lozinka +
                "," + ime +
                "," + prezime +
                "," + adresa +
                "," + pol +
                "," + brojTelefona;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public static Korisnik pronadjiPoJMBG(String JMBG) {
        List<Korisnik> listaKorisnika = Korisnik.ucitajSveKorisnike();
        for (Korisnik korisnik : listaKorisnika) {
            if (korisnik.getJMBG() == Long.valueOf(JMBG)) {
                return korisnik;
            }
        }
        return null;
    }
}