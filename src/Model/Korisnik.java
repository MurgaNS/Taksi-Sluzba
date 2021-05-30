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
    protected Pol pol;
    protected String brojTelefona;
    protected boolean obrisan;

    public enum Pol {
        MUSKI,
        ZENSKI
    }

    public Korisnik(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, Boolean obrisan) {
        this.JMBG = JMBG;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.pol = pol;
        this.brojTelefona = brojTelefona;
        this.obrisan = obrisan;
    }

    public static Korisnik postojiKorisnik(String korisnickoIme, String lozinka) {
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
        for (Korisnik k : korisnici) {
            if (k.getKorisnickoIme().equals(korisnickoIme) && k.getLozinka().equals(lozinka)) {
                return k;
            }
        }
        return null;
    }


    public static Korisnik nadjiKorisnikaPrekoJMBG(long JMBG) {
        List<Korisnik> korisnici = ucitajSveKorisnike();
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == JMBG) {
                return korisnik;
            }
        }
        return null;
    }

    public static List<Korisnik> ucitajSveKorisnike() {
        List<Korisnik> sviKorisnici = new ArrayList<>();
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Korisnik korisnik = null;
                String[] lineParts = line.split(",");
                Pol pol;
                if (lineParts[7].trim().equals("MUSKI")) {
                    pol = Pol.MUSKI;
                } else {
                    pol = Pol.ZENSKI;
                }
                switch (lineParts[0]) {
                    case "musterija" -> korisnik = new Musterija(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], Boolean.parseBoolean(lineParts[9]));
                    case "vozac" -> korisnik = new Vozac(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], Boolean.parseBoolean(lineParts[9]), Double.parseDouble(lineParts[10]), Integer.parseInt(lineParts[11]));
                    case "dispecer" -> {
                        Dispecer.OdeljenjeRada odeljenjeRada;
                        if (lineParts[11].trim().equals("PRIJEM_VOZNJE")) {
                            odeljenjeRada = Dispecer.OdeljenjeRada.PRIJEM_VOZNJE;
                        } else {
                            odeljenjeRada = Dispecer.OdeljenjeRada.REKLAMACIJE;
                        }
                        korisnik = new Dispecer(Long.parseLong(lineParts[1]), lineParts[2], lineParts[3], lineParts[4], lineParts[5], lineParts[6], pol, lineParts[8], Boolean.parseBoolean(lineParts[9]), Double.parseDouble(lineParts[10]), lineParts[11], odeljenjeRada);
                    }
                }
                sviKorisnici.add(korisnik);
            }

            bufferedReader.close();

        } catch (FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        }
        return sviKorisnici;

    }

    public static void upisiSveKorisnike(List<Korisnik> korisnici) {
        File file = new File("src\\Data\\korisnici.csv");
        try {
            PrintWriter writer = new PrintWriter(file);
            for (Korisnik korisnik : korisnici) {
                writer.write(korisnik.korisnikUString() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }


    public String korisnikUString() {
        return JMBG +
               "," + korisnickoIme +
               "," + lozinka +
               "," + ime +
               "," + prezime +
               "," + adresa +
               "," + pol +
               "," + brojTelefona +
               "," + obrisan;
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

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
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

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }
}