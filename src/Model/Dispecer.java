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

    public String korisnikUString(){
        return "dispecer,"+super.korisnikUString() + "," + plata + "," + brojTelefonskeLinije + "," + odeljenjeRada;
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
        System.out.println("11. Prikaz voznji");


    }

    public static void prikaziVozace() {
        List<Korisnik> sviKorisnici = Korisnik.ucitajSveKorisnike();
        for (Korisnik korisnik : sviKorisnici) {
            if (korisnik instanceof Vozac) {
                System.out.println("JMBG:" + korisnik.getJMBG() + " " + "Korisnicko ime:" + korisnik.getKorisnickoIme() + " " +
                        "Lozinka:" + korisnik.getLozinka() + " " + "Ime:" + korisnik.getIme() + " "  + "Prezime:" +
                        korisnik.getPrezime()+ " " + "Adresa: " + korisnik.getAdresa()+ " " + "Pol:" + korisnik.getPol() + " " +
                        "Broj telefona:" + korisnik.getBrojTelefona() + " " + "Plata:" + ((Vozac) korisnik).getPlata() + " " +
                        "Broj clanske karte:" + ((Vozac) korisnik).getBrojClanskeKarte());
            }
        }
    }


    public static void dodajVozaca(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi JMBG");
        long JMBG = scanner.nextLong();
        System.out.println("Unesi korisnicko ime");
        String korisnickoIme = scanner.next();
        System.out.println("Unesi lozinku");
        String lozinka = scanner.next();
        System.out.println("Unesi ime: ");
        String ime = scanner.next();
        System.out.println("Unesi prezime:");
        String prezime = scanner.next();
        System.out.println("Unesi adresu");
        String adresa = scanner.next();
        System.out.println("Unesi pol");
        String pol = scanner.next();
        System.out.println("Broj telefona");
        String brojTelefona = scanner.next();
        System.out.println("Unesi platu");
        double plata = scanner.nextDouble();
        System.out.println("Unesi broj članske karte");
        int brojClanskeKarte = scanner.nextInt();

        Vozac vozac = new Vozac(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, plata, brojClanskeKarte);
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
        korisnici.add(vozac);
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno upisan vozač");
    }


    public static void izmeniVozaca() {
        System.out.println("Unesi JMBG vozaca kojeg zelite da izmenite");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Vozac vozac = null;
        for(Korisnik korisnik : korisnici){
            if(korisnik.getJMBG() == JMBG){
                vozac = (Vozac) korisnik;
            }
        }
        if(vozac == null){
            return;
        }
        System.out.println("1. Izmena korisnickog imena" +
                "\n2. Izmena sifre" +
                "\n3. Izmena imena" +
                "\n4. Izmena prezimena" +
                "\n5. Izmena adrese" +
                "\n6. Izmena pola" +
                "\n7. Izmena broja telefona" +
                "\n8. Izmena plate" +
                "\n9. Izmena clanske karte");
        System.out.println("Odaberi opciju");
        int opcija = scanner.nextInt();
        switch (opcija) {
            case 1:
                System.out.println("Unesi korisnicko ime: ");
                String korisnickoIme = scanner.next();
                vozac.setKorisnickoIme(korisnickoIme);
                break;
            case 2:
                System.out.println("Unesi lozinku: ");
                String lozinka = scanner.next();
                vozac.setLozinka(lozinka);
                break;
            case 3:
                System.out.println("Unesi ime: ");
                String ime = scanner.next();
                vozac.setIme(ime);
                break;
            case 4:
                System.out.println("Unesi prezime: ");
                String prezime = scanner.next();
                vozac.setPrezime(prezime);
                break;
            case 5:
                System.out.println("Unesite adresu: ");
                String adresa = scanner.next();
                vozac.setAdresa(adresa);
                break;
            case 6:
                System.out.println("Unesite pol: ");
                String pol = scanner.next();
                vozac.setPol(pol);
                break;

            case 7:
                System.out.println("Unesite broj telefona: ");
                String brojTelefona = scanner.nextLine();
                vozac.setBrojTelefona(brojTelefona);
                break;

            case 8:
                System.out.println("Unesite platu: ");
                Double plata = scanner.nextDouble();
                vozac.setPlata(plata);
                break;

            case 9:
                System.out.println("Unesite broj clanske karte");
                int brojClanskeKarte = scanner.nextInt();
                vozac.setBrojClanskeKarte(brojClanskeKarte);
                break;

        }


        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno upisan korisnik");
        }


    public static void upisiVozaca(Vozac vozac) {
        File file = new File("src\\Data\\korisnici.csv");
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file, true));
            writer.append("\nvozac,"+vozac.getJMBG()+","+vozac.getKorisnickoIme()+","
                    +vozac.getLozinka()+","+vozac.getIme()+","+
                    vozac.getPrezime()+","+vozac.getAdresa()+","+vozac.getPol()+","+vozac.getBrojTelefona()+","+
                    vozac.getPlata()+","+vozac.getBrojClanskeKarte());
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException exception){
            System.out.println("Nepostojeći fajl");
        }
    }


    public static void izbrisiVozaca() {
        System.out.println("Unesi JMBG vozaca kojeg zelite da obrisete");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Vozac vozac = null;
        for(Korisnik korisnik : korisnici){
            if(korisnik.getJMBG() == JMBG){
                vozac = (Vozac) korisnik;
            }
        }
        if(vozac == null){
            return;
        }
        korisnici.remove(vozac);
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno ste obrisali vozača!");

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