package Model;

import java.io.*;
import java.util.*;


public class Dispecer extends Korisnik {


    protected double plata;
    protected String brojTelefonskeLinije;
    protected String odeljenjeRada;

    public Dispecer() {
    }

    public Dispecer(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona, double plata, String brojTelefonskeLinije, String odeljenjeRada) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojTelefonskeLinije = brojTelefonskeLinije;
        this.odeljenjeRada = odeljenjeRada;
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

    public String getOdeljenjeRada() {
        return odeljenjeRada;
    }

    public void setOdeljenjeRada(String odeljenjeRada) {
        this.odeljenjeRada = odeljenjeRada;
    }

    protected static TaksiSluzba preuzmiPodatkeOTaksiSluzbi() {
        TaksiSluzba taksiSluzba = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/Data/taksiSluzba.csv"))) {
            String[] red = br.readLine().split(",");
            taksiSluzba = new TaksiSluzba(Long.parseLong(red[0]), red[1], red[2], Double.parseDouble(red[3]), Double.parseDouble(red[4]));
            return taksiSluzba;
        } catch (FileNotFoundException e) {
            System.out.println("Fajl ne postoji.");
        } catch (IOException e) {
            System.out.println("Doslo je do greske prilikom ucitavanja fajla.");
        }
        return taksiSluzba;
    }

    public static void prikazPodatakaOTaksiSluzbi() {
        TaksiSluzba taksiSluzba = preuzmiPodatkeOTaksiSluzbi();
        System.out.println(taksiSluzba.toString());
    }

    public static void izmenaPodatakaTaksiSluzbe() throws IOException {
        TaksiSluzba taksiSluzba = preuzmiPodatkeOTaksiSluzbi();
        Scanner sc = new Scanner(System.in);
        System.out.println("1.PIB");
        System.out.println("2.Naziv");
        System.out.println("3.Adresa");
        System.out.println("4.Cena starta voznje");
        System.out.println("5.Cena po kilometru");
        System.out.println("6.Svi podaci");
        int line = sc.nextInt();
        switch (line) {
            case 1 -> izmeniPIB(taksiSluzba);
            case 2 -> izmeniNaziv(taksiSluzba);
            case 3 -> izmeniAdresu(taksiSluzba);
            case 4 -> izmeniCenuStartaVoznje(taksiSluzba);
            case 5 -> izmeniCenuVoznjePoKilometru(taksiSluzba);
            case 6 -> izmeniSvePodatkeTaksiSluzbe(taksiSluzba);
        }
        sacuvajPodatkeUFajl(taksiSluzba);
    }

    protected static void sacuvajPodatkeUFajl(TaksiSluzba taksiSluzba) throws IOException {
        File file = new File("src/Data/taksiSluzba.csv");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(taksiSluzba.stringToSave());
        fileWriter.close();
        System.out.println("Uspesno ste izmenili podatke o taksi sluzbi.");
    }

    protected static void izmeniSvePodatkeTaksiSluzbe(TaksiSluzba taksiSluzba) {
        izmeniPIB(taksiSluzba);
        izmeniNaziv(taksiSluzba);
        izmeniAdresu(taksiSluzba);
        izmeniCenuStartaVoznje(taksiSluzba);
        izmeniCenuVoznjePoKilometru(taksiSluzba);
    }

    protected static void izmeniCenuVoznjePoKilometru(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cena voznje po kilometru:");
        double cenaVoznjePoKilometru = sc.nextDouble();
        taksiSluzba.setCenaPoKilometru(cenaVoznjePoKilometru);
    }

    protected static void izmeniCenuStartaVoznje(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Cena starta voznje:");
        double cenaStartaVoznje = sc.nextDouble();
        taksiSluzba.setCenaStarta(cenaStartaVoznje);
    }

    protected static void izmeniAdresu(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Adresa:");
        String adresa = sc.nextLine();
        taksiSluzba.setAdresa(adresa);
    }

    protected static void izmeniNaziv(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Naziv:");
        String naziv = sc.nextLine();
        taksiSluzba.setNaziv(naziv);
    }

    protected static void izmeniPIB(TaksiSluzba taksiSluzba) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PIB: ");
        long PIB = sc.nextInt();
        taksiSluzba.setPIB(PIB);
        System.out.println(taksiSluzba.toString());
    }

    protected void prikaziVozace(List<Vozac> vozaci) {
    }

    protected boolean dodajVozaca(Vozac vozac) {
        return false;
    }

    protected boolean izmeniVozaca(Vozac vozac) {
        return false;
    }

    protected boolean izbrisiVozaca(Vozac vozac) {
        return false;
    }

    protected void prikaziAutomobile(List<Automobil> automobili) {
    }

    protected boolean dodajAutomobil(Automobil automobil) {
        return false;
    }

    protected boolean izmeniAutomobil(Automobil automobil) {
        return false;
    }

    protected boolean izbrisiAutomobil(Automobil automobil) {
        return false;
    }

    protected void prikazSvihVoznji(List<Voznja> listaVoznji) {
    }

    protected boolean dodeljivanjeVoznje(Vozac vozac, Voznja voznja) {
        return false;
    }

    protected List<Vozac> pretragaVozaca(String kriterijum) {
        return null;
    }

    protected List<Automobil> pretragaAutomobila(String kriterijum) {
        return null;
    }

    protected void dnevniIzvestaj() {
    }

    protected void nedeljniIzvestaj() {
    }

    protected void mesecniIzvestaj() {
    }

    protected void godisnjiIzvestaj() {
    }

    protected void dnevniIzvestajVozaca(Vozac vozac) {
    }

    protected void nedeljniIzvestajVozaca(Vozac vozac) {
    }

    protected void mesecniIzvestajVozaca(Vozac vozac) {
    }

    protected void godisnjiIzvestajVozaca(Vozac vozac) {
    }

}