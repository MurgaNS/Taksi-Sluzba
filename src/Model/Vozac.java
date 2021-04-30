package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Vozac extends Korisnik {

    protected double plata;
    protected int brojClanskeKarte;
    protected List<Voznja> listaVoznji;
    protected Automobil automobil;

    public Vozac() {
    }

    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona, double plata, int brojClanskeKarte, List<Voznja> listaVoznji, Automobil automobil) {
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

    public String korisnikUString(){
        return "vozac,"+super.korisnikUString() + "," + plata + "," + brojClanskeKarte;
    }

    public static void prikaziMeni() {
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

    public List<Voznja> getListaVoznji() {
        return listaVoznji;
    }

    public void setListaVoznji(List<Voznja> listaVoznji) {
        this.listaVoznji = listaVoznji;
    }

    public Automobil getAutomobil() {
        return automobil;
    }

    public void setAutomobil(Automobil automobil) {
        this.automobil = automobil;
    }

    protected void prikazIstorijeSpostvenihVoznji(Vozac vozac) {
        for (Voznja voznja : vozac.getListaVoznji()) {
            System.out.println(voznja.toString());
        }
    }

    protected void prikazVoznjiPutemAplikacije(ArrayList<Voznja> listaVoznji) {
//        todo prihvatanje/odbijanje voznje
        for (Voznja voznja : listaVoznji) {
            if (voznja.getNacinPorudzbine() == "putem aplikacije") {
                System.out.println(voznja.toString());
            }
        }
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

    public static Vozac pronadjiPoJMBG(Long JMBG) {
        List<Vozac> vozaci = ucitajSveVozace();
        for (Vozac vozac : vozaci) {
            if (vozac.getJMBG() == JMBG) {
                return vozac;
            }
        }
        return null;
    }

    protected static void nedeljniIzvestaj(Vozac vozac) {
        List<Voznja> listaVoznji = voznjePrethodnihNedeljuDana(vozac);
        for (Voznja voznja : listaVoznji
        ) {
            System.out.println(voznja.toString());
        }
    }

    protected static List<Voznja> voznjePrethodnihNedeljuDana(Vozac vozac) {
        List<Voznja> listaVoznji = vozac.getListaVoznji();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        for (Voznja voznja : listaVoznji) {
            if (voznja.getDatumPorudzbine().after(cal.getTime())) {
                listaVoznji.add(voznja);
            }
        }
        return listaVoznji;
    }

    public static ArrayList<Vozac> vozaciBezAutomobila() {
        ArrayList<Vozac> vozaci = ucitajSveVozace();
        ArrayList<Vozac> retVal = new ArrayList<>();
        for (Vozac vozac : vozaci) {
            if (vozac.getAutomobil() == null) {
                retVal.add(vozac);
            }
        }
        return retVal;
    }

    protected static ArrayList<Vozac> ucitajSveVozace() {
        // TODO dodati listu voznji
        ArrayList<Vozac> vozaci = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/korisnici.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                if (tmp[0].equals("vozac")) {
                    Automobil automobil = null;
                    try {
                        if (Automobil.pronadjiPoBrojuTaksiVozila(tmp[11]) != null) {
                            automobil = Automobil.pronadjiPoBrojuTaksiVozila(tmp[11]);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    Vozac vozac = new Vozac(Long.parseLong(tmp[1]), tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], tmp[7], tmp[8], Double.parseDouble(tmp[9]), Integer.parseInt(tmp[10]), null, automobil);
                    vozac.setListaVoznji(ucitajListuVoznji(vozac));
                    vozaci.add(vozac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    public static ArrayList<Voznja> ucitajListuVoznji(Vozac vozac) {
        ArrayList<Voznja> listaVoznji = null;
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/voznje.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                if (tmp[8].equals(String.valueOf(vozac.getJMBG()))) {
                    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    Date date = (Date) formatter.parse(tmp[1]);
                    Musterija musterija = new Musterija();
                    Voznja voznja = new Voznja(Long.parseLong(tmp[0]), date, tmp[2], tmp[3], Double.parseDouble(tmp[4]), Double.parseDouble(tmp[5]), tmp[6], tmp[7], null, musterija);
                    listaVoznji.add(voznja);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaVoznji;
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