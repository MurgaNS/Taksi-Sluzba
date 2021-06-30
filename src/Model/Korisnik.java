package Model;

import java.io.*;

import StrukturePodataka.ArrayList;


public abstract class Korisnik {

    protected Long JMBG;
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

    public Korisnik(Long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, Boolean obrisan) {
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
        ArrayList<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
        for (Korisnik k : korisnici) {
            if (k.getKorisnickoIme().equals(korisnickoIme) && k.getLozinka().equals(lozinka)) {
                return k;
            }
        }
        return null;
    }

    public static Pol ucitajPol(String pol) {
        if (pol.trim().equals("MUSKI")) {
            return Pol.MUSKI;
        } else if (pol.trim().equals("ZENSKI")) {
            return Pol.ZENSKI;
        } else {
            return null;
        }
    }

    public static Korisnik nadjiKorisnikaPrekoJMBG(Long JMBG) {
        ArrayList<Korisnik> korisnici = ucitajSveKorisnike();
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG().equals(JMBG)) {
                return korisnik;
            }
        }
        return null;
    }

    public static ArrayList<Korisnik> ucitajSveKorisnike() {
        ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Korisnik korisnik = null;
                String[] lineParts = line.split(",");
                String uloga = lineParts[0];
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
                switch (uloga) {
                    case "musterija" -> korisnik = new Musterija(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
                    case "vozac" -> {
                        double plata = Double.parseDouble(lineParts[11]);
                        int brClanskeKarte = Integer.parseInt(lineParts[12]);
                        Long brTaksiVozila = null;
                        double brojUspesnihVoznji= Double.parseDouble(lineParts[14]);
                        korisnik = new Vozac(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brClanskeKarte, null, brTaksiVozila,brojUspesnihVoznji);
                        Vozac.ucitajVoziloVozacu((Vozac) korisnik);
                    }
                    case "dispecer" -> {
                        double plata = Double.parseDouble(lineParts[11]);
                        String brTelLinije = lineParts[12];
                        Dispecer.OdeljenjeRada odeljenjeRada = Dispecer.ucitajOdeljenjeRada(lineParts[13]);
                        korisnik = new Dispecer(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brTelLinije, odeljenjeRada);
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
    public static void upisiSveKorisnike(ArrayList<Korisnik> korisnici) {
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

    public Long getJMBG() {
        return JMBG;
    }

    public void setJMBG(Long JMBG) {
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