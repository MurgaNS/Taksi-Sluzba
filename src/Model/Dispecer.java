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
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/Data/taksiSluzba.csv"));
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

    public static void izmenaPodatakaTaksiSluzbeMenu() {
        System.out.println("1.PIB");
        System.out.println("2.Naziv");
        System.out.println("3.Adresa");
        System.out.println("4.Cena starta voznje");
        System.out.println("5.Cena po kilometru");
        System.out.println("6.Svi podaci");
    }

    public static void izmenaPodatakaTaksiSluzbe() throws IOException {
        TaksiSluzba taksiSluzba = preuzmiPodatkeOTaksiSluzbi();
        Scanner sc = new Scanner(System.in);
        izmenaPodatakaTaksiSluzbeMenu();
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

    public static void prikaziMeni() {
        System.out.println("1. Prikaz podataka taksi službe");
        System.out.println("2. Izmena podataka o taksi službi");
        System.out.println("3. Prikaz vozača");
        System.out.println("4. Dodavanje vozača");
        System.out.println("5. Izmena vozača");
        System.out.println("6. Brisanje vozača");
        System.out.println("7. Dodavanje automobila");
        System.out.println("8. Prikaz automobila");
        System.out.println("9. Izmena automobila");
        System.out.println("10. Brisanje automobila");

    }

//    public static void prikaziVozace() {
//        List<Korisnik> sviKorisnici = Dispecer.ucitajSveKorisnike();
//        for (Korisnik korisnik : sviKorisnici) {
//            if (korisnik instanceof Vozac) {
//                System.out.println(korisnik.getIme() + " " + korisnik.getJMBG());
//            }
//        }
//    }
//
//
//    public static boolean dodajVozaca(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Unesi JMBG");
//        long JMBG = scanner.nextLong();
//        System.out.println("Unesi korisnicko ime");
//        String korisnickoIme = scanner.nextLine();
//
//        // unesi ostale podatke
//        // napravi vozac objekat
//        // i pozovi se na metodu upisiVozaca
//
//
//    }
//
//    public static void upisiVozaca(Vozac vozac) throws FileNotFoundException {
//        File file = new File("");
//        try {
//            PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
//            writer.append("\nvozac"+vozac.getJMBG()+","+vozac.getKorisnickoIme()+","
//                    +vozac.getLozinka()+","+vozac.getIme()+","+
//                    vozac.getPrezime()+","+vozac.getAdresa()+","+vozac.getPol()+","+vozac.getBrojTelefona()+","+
//                    vozac.getPlata()+","+vozac.getBrojClanskeKarte());
//            writer.flush();
//            writer.close();
//        }
//        catch (FileNotFoundException exception){
//            System.out.println("Nepostojeći fajl");
//        }
//    }

    protected boolean izmeniVozaca() {
        return false;
    }

    protected boolean izbrisiVozaca() {
        return false;
    }


    public static void prikaziAutomobile() throws IOException {
        ArrayList<Automobil> automobili = Automobil.ucitajSveAutomobile();
        for (Automobil automobil : automobili) {
            System.out.println(automobil.toString());
        }
    }

    public static void dodajAutomobil() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Broj taksi vozila: ");
        String brTaksiVozila = sc.nextLine();
        System.out.println("Model: ");
        String model = sc.nextLine();
        System.out.println("Proizvodjac: ");
        String proizvodjac = sc.nextLine();
        System.out.println("Godina proizvodnje: ");
        int godinaProizvodnje = sc.nextInt();
        System.out.println("Registarska oznaka: ");
        String regOznaka = sc.nextLine();
        System.out.println("Vrsta: ");
        String vrsta = sc.nextLine();
        Automobil automobil = new Automobil(brTaksiVozila, model, proizvodjac, godinaProizvodnje, regOznaka, vrsta);
        System.out.println("Da li zelite da dodate vozaca ovom automobilu? [Y/N]");
        if (sc.nextLine().equals("Y")) {
            if (dodajAutomobiluVozaca(automobil)) {
                System.out.println("Uspesno ste dodali vozaca");
            } else {
                System.out.println("Doslo je do greske. Vozac sa tim JMBG ne postoji.");
            }
        }
        Automobil.sacuvajAutomobilUFajl(automobil);
    }

    private static boolean dodajAutomobiluVozaca(Automobil automobil) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vozac> vozaci = Vozac.vozaciBezAutomobila();
        if (vozaci != null) {
            for (Vozac vozac : vozaci) {
                if (vozac.getAutomobil() == null) {
                    System.out.println(vozac.toString());
                }
            }
            System.out.println("Izaberite JMBG vozaca kojem dodeljujete automobil: ");
            String JMBG = sc.nextLine();
            Vozac vozac = null;
            if ((vozac = Vozac.pronadjiPoJMBG(JMBG)) != null) {
                automobil.setVozac(vozac.getJMBG());
                return true;
            }
        }
        automobil.setVozac(Long.valueOf(0));
        return false;
    }
}