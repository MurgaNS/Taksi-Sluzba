package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dispecer extends Korisnik {
    private long id;
    private double plata;
    private String brojTelefonskeLinije;
    private OdeljenjeRada odeljenjeRada;

    public enum OdeljenjeRada {
        PRIJEM_VOZNJE,
        REKLAMACIJE
    }

    public Dispecer(long id, long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, boolean obrisan, double plata, String brojTelefonskeLinije, OdeljenjeRada odeljenjeRada) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
        this.id = id;
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
               "id=" + id +
               ", plata=" + plata +
               ", brojTelefonskeLinije='" + brojTelefonskeLinije + '\'' +
               ", odeljenjeRada=" + odeljenjeRada +
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

    public static OdeljenjeRada ucitajOdeljenjeRada(String odeljenjeRada) {
        if (odeljenjeRada.trim().equals("PRIJEM_VOZNJE")) {
            return Dispecer.OdeljenjeRada.PRIJEM_VOZNJE;
        } else if (odeljenjeRada.trim().equals("REKLAMACIJE")) {
            return Dispecer.OdeljenjeRada.REKLAMACIJE;
        } else {
            return null;
        }
    }

    public static Dispecer ucitajDispeceraIzFajla(String[] lineParts) {
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
        double plata = Double.parseDouble(lineParts[11]);
        String brTelLinije = lineParts[12];
        Dispecer.OdeljenjeRada odeljenjeRada = Dispecer.ucitajOdeljenjeRada(lineParts[12]);
        return new Dispecer(id, jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan, plata, brTelLinije, odeljenjeRada);

    }

    public static List<Dispecer> ucitajSveDispecere() {
        List<Dispecer> listaDispecera = new ArrayList<>();
        File file = new File("src\\Data\\korisnici.csv");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineParts = line.split(",");
                String uloga = lineParts[0];
                if (uloga.equals("dispecer")) {
                    listaDispecera.add(ucitajDispeceraIzFajla(lineParts));
                }
            }
            bufferedReader.close();
        } catch (
                FileNotFoundException exception) {
            System.out.println("Fajl nije pronadjen");
        } catch (
                IOException exception) {
            exception.printStackTrace();
            System.out.println("Greska pri citanju datoteke");
        }
        return listaDispecera;
    }

    public static List<Long> listaIdDispecer() {
        List<Long> listaId = new ArrayList<>();
        for (Dispecer d : ucitajSveDispecere()) {
            listaId.add(d.getId());
        }
        return listaId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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