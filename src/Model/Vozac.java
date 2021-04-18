package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Vozac extends Korisnik {

    protected double plata;
    protected int brojClanskeKarte;
    protected Set<Voznja> listaVoznji;
    protected Automobil automobil;

    public Vozac() {
    }

    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona, double plata, int brojClanskeKarte, Set<Voznja> listaVoznji, Automobil automobil) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.listaVoznji = listaVoznji;
        this.automobil = automobil;
    }

    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona, double plata, int brojClanskeKarte) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public int getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(int brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public Set<Voznja> getListaVoznji() {
        return listaVoznji;
    }

    public void setListaVoznji(Set<Voznja> listaVoznji) {
        this.listaVoznji = listaVoznji;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    protected void prikazIstorijeSpostvenihVoznji() {
    }

    protected void prikazVoznjiPutemAplikacije() {
    }

    protected void prikazVoznjiPutemTelefona() {
    }

    protected boolean krajVoznje(double predjeniKilometri, double trajanjeVoznje) {
        return false;
    }

    protected void dnevniIzvestaj() {
    }

    protected void nedeljniIzvestaj() {
    }

    protected void mesecniIzvestaj() {
    }

    protected void godisnjiIzvestaj() {
    }

    protected void aukcija(int minutaDoDolaska) {
    }

    protected static Vozac pronadjiPoJMBG(String JMBG) {
        List<Vozac> vozaci = ucitajSveVozace();
        for (Vozac vozac : vozaci) {
            if (String.valueOf(vozac.getJMBG()).equals(JMBG)) {
                return vozac;
            }
        }
        return null;
    }

    protected static List<Vozac> ucitajSveVozace() {
        // bitno je da na prvom mestu u fajlu bude uloga
        // TODO dodati listu voznji i automobil
        List<Vozac> vozaci = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/korisnici.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                if (tmp[0].equals("vozac")) {
                    Vozac vozac = new Vozac(Long.parseLong(tmp[1]), tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7], tmp[8], Double.parseDouble(tmp[9]), Integer.parseInt(tmp[10]));
                    vozaci.add(vozac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    @Override
    public String toString() {
        return "Vozac{" +
                "JMBG=" + JMBG +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", adresa='" + adresa + '\'' +
                ", pol='" + pol + '\'' +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", plata=" + plata +
                ", brojClanskeKarte=" + brojClanskeKarte +
                ", listaVoznji=" + listaVoznji +
                ", automobil=" + automobil +
                '}';
    }
}