package Model;

import java.io.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Dispecer extends Korisnik {


    private double plata;
    private String brojTelefonskeLinije;
    private String odeljenjeRada;

    public Dispecer() {
    }

    public Dispecer(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, String pol, String brojTelefona, double plata, String brojTelefonskeLinije, String odeljenjeRada) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojTelefonskeLinije = brojTelefonskeLinije;
        this.odeljenjeRada = odeljenjeRada;
    }

    public String korisnikUString() {
        return "dispecer," + super.korisnikUString() + "," + plata + "," + brojTelefonskeLinije + "," + odeljenjeRada;
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
        System.out.println("12. Prikaz voznji dodeljenih putem telefona");
        System.out.println("13. Kombinovana pretraga vozaca");
        System.out.println("14. Izveštaj");


    }

    public static void prikaziVozace() {
        List<Korisnik> sviKorisnici = Korisnik.ucitajSveKorisnike();
        for (Korisnik korisnik : sviKorisnici) {
            if (korisnik instanceof Vozac) {
                System.out.println("JMBG:" + korisnik.getJMBG() + " " + "Korisnicko ime:" + korisnik.getKorisnickoIme() + " " +
                        "Lozinka:" + korisnik.getLozinka() + " " + "Ime:" + korisnik.getIme() + " " + "Prezime:" +
                        korisnik.getPrezime() + " " + "Adresa: " + korisnik.getAdresa() + " " + "Pol:" + korisnik.getPol() + " " +
                        "Broj telefona:" + korisnik.getBrojTelefona() + " " + "Plata:" + ((Vozac) korisnik).getPlata() + " " +
                        "Broj clanske karte:" + ((Vozac) korisnik).getBrojClanskeKarte());
            }
        }
    }


    public static void dodajVozaca() {
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

        Automobil.ispisiSvaSlobodnaVozila();
        System.out.println("Odaberi vozilo(unesi broj registarske oznake): ");

        String brRegistarskeOznake = scanner.next();

        List<Automobil> automobili = Automobil.ucitajSveAutomobile();
        for (Automobil automobil : automobili) {
            if (automobil.getBrRegistarskeOznake().equals(brRegistarskeOznake)) {
                automobil.setVozac(JMBG);
                break;
            }
        }
        Automobil.sacuvajListuAutomobilaUFajl(automobili);

        Vozac vozac = new Vozac(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, plata, brojClanskeKarte);
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
        korisnici.add(vozac);
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno upisan vozač");
    }

    public static void izbrisiAutomobil() throws IOException {
        Automobil.prikaziAutomobile();
        Scanner sc = new Scanner(System.in);
        System.out.println("Izaberi broj taksi vozila: ");
        String brTaksiVozila = sc.nextLine();
        Automobil automobil = Automobil.pronadjiPoBrojuTaksiVozila(brTaksiVozila);
        Automobil.izbrisiAutomobil(automobil);
    }

    public static void izmeniVozaca() {
        System.out.println("Unesi JMBG vozaca kojeg zelite da izmenite");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Vozac vozac = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == JMBG) {
                vozac = (Vozac) korisnik;
            }
        }
        if (vozac == null) {
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
            writer.append("\nvozac," + vozac.getJMBG() + "," + vozac.getKorisnickoIme() + ","
                    + vozac.getLozinka() + "," + vozac.getIme() + "," +
                    vozac.getPrezime() + "," + vozac.getAdresa() + "," + vozac.getPol() + "," + vozac.getBrojTelefona() + "," +
                    vozac.getPlata() + "," + vozac.getBrojClanskeKarte());
            writer.flush();
            writer.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Nepostojeći fajl");
        }
    }


    public static void izbrisiVozaca() {
        System.out.println("Unesi JMBG vozaca kojeg zelite da obrisete");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Vozac vozac = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == JMBG) {
                vozac = (Vozac) korisnik;
            }
        }
        if (vozac == null) {
            return;
        }
        korisnici.remove(vozac);
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno ste obrisali vozača!");
    }

    public static void kombinovanaPretragaVozaca() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi ime");
        String ime = scanner.next();
        System.out.println("Unesi prezime");
        String prezime = scanner.next();
        System.out.println("Min platu");
        double minPlata = scanner.nextDouble();
        System.out.println("Max platu");
        double maxPlata = scanner.nextDouble();

        System.out.println("Unesi proizvodjac automobila : ");
        String proizvodjac = scanner.next();

        List<Vozac> sviKorisnici = Vozac.nadjiVozaceZaProizvodjaca(proizvodjac);

        for (Vozac vozac : sviKorisnici) {
            if (vozac.getIme().equalsIgnoreCase(ime) && vozac.getPrezime().equalsIgnoreCase(prezime)
                    && ((Vozac) vozac).getPlata() >= minPlata && ((Vozac) vozac).getPlata() <= maxPlata) {
                System.out.println(vozac);
            }
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
        int godinaProizvodnje = Integer.parseInt(sc.nextLine());
        System.out.println("Registarska oznaka: ");
        String regOznaka = sc.nextLine();
        System.out.println("Vrsta: ");
        String vrsta = sc.nextLine();
        Automobil automobil = new Automobil(brTaksiVozila, model, proizvodjac, godinaProizvodnje, regOznaka, vrsta, false);
        System.out.println("Da li zelite da dodate vozaca ovom automobilu? [Y/N]");
        if (sc.nextLine().equals("Y")) {
            dodajAutomobiluVozaca(automobil);
        }
        Automobil.sacuvajAutomobilUFajl(automobil);
    }

    public static void izvestaj(){
        System.out.println("1. Dnevni izveštaj");
        System.out.println("2. Nedeljni izveštaj");
        System.out.println("3. Mesečni izveštaj");
        System.out.println("4. Godišnji izveštaj");
        Scanner scanner = new Scanner(System.in);
        int izbor = scanner.nextInt();
        switch (izbor){
            case 1:
                dnevniIzvestaj();
                break;
            default:
                System.out.println("Nepostojeća komanda");
                break;
        }



    }

    public static List<Voznja> nedeljneVoznje(){
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        List<Voznja> nedeljneVoznje = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date monday = c.getTime();

        Date nextMonday= new Date(monday.getTime()+7*24*60*60*1000);
        for(Voznja voznja : voznje){
            if(voznja.getDatumPorudzbine().after(monday) && voznja.getDatumPorudzbine().before(nextMonday)){
                nedeljneVoznje.add(voznja);
            }
        }

        return nedeljneVoznje;


    }

    public static List<Voznja> danasnjeVoznje(){
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        Date danasnjiDatum = new Date();
        Instant instant2 = danasnjiDatum.toInstant().truncatedTo(ChronoUnit.DAYS);
        List<Voznja> danasnjeVoznje = new ArrayList<>();
        for(Voznja voznja : voznje){
            Instant instant1 = voznja.getDatumPorudzbine().toInstant().truncatedTo(ChronoUnit.DAYS);
            if(instant1.equals(instant2)){
                danasnjeVoznje.add(voznja);
            }
        }
        return danasnjeVoznje;
    }


    public static void dnevniIzvestaj(){
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        Date danasnjiDatum = new Date();
        Instant instant2 = danasnjiDatum.toInstant().truncatedTo(ChronoUnit.DAYS);
        List<Voznja> danasnjeVoznje = new ArrayList<>();
        for(Voznja voznja : voznje){
            Instant instant1 = voznja.getDatumPorudzbine().toInstant().truncatedTo(ChronoUnit.DAYS);
            if(instant1.equals(instant2)){
                danasnjeVoznje.add(voznja);
            }
        }
        System.out.println("Ukupan broj vožnji: " + danasnjeVoznje.size());
        int brojVoznjiPorucenihAplikacijom = 0;
        int brojVoznjiPorucenihTelefonom = 0;
        int brojAktivnihVozaca = 0;
        double ukupnoTrajanjeVoznji = 0;
        double ukupanBrojPredjenihKilometara = 0;
        double ukupnaZarada = 0;
        TaksiSluzba taksiSluzba = TaksiSluzba.preuzmiPodatkeOTaksiSluzbi();

        for(Voznja voznja : danasnjeVoznje){
            if(voznja.getNacinPorudzbine().equals("APLIKACIJOM")){
                brojVoznjiPorucenihAplikacijom++;
            }
            if(voznja.getNacinPorudzbine().equals("TELEFONOM")){
                brojVoznjiPorucenihTelefonom++;
            }
            if(voznja.getVozac() != null){
                brojAktivnihVozaca++;
            }
            ukupnoTrajanjeVoznji += voznja.getTrajanjeVoznjeUMinutama();
            ukupanBrojPredjenihKilometara += voznja.getBrojPredjenihKilometara();
            ukupnaZarada += taksiSluzba.getCenaStarta() + voznja.getBrojPredjenihKilometara()*taksiSluzba.getCenaPoKilometru();

        }
        System.out.println("Broj vožnji poručenih aplikacijom: " + brojVoznjiPorucenihAplikacijom);
        System.out.println("Broj vožnji poručenih telefonom: " + brojVoznjiPorucenihTelefonom);
        System.out.println("Broj aktivnih vozača: " + brojAktivnihVozaca);

        double prosecnoTrajanjeVoznji = ukupnoTrajanjeVoznji / voznje.size();
        double prosecanBrojPredjenihKilometara = ukupanBrojPredjenihKilometara / voznje.size();
        System.out.println("Prosečno trajanje vožnji: " + prosecnoTrajanjeVoznji);
        System.out.println("Prosečan broj pređenih kilometara: " + prosecanBrojPredjenihKilometara);

        System.out.println("Ukupna zarada za sve vožnje " + ukupnaZarada);

        double prosecnaZarada = ukupnaZarada / voznje.size();

        System.out.println("Prosečna zarada po vožnji: " + prosecnaZarada);







    }

    private static void dodajAutomobiluVozaca(Automobil automobil) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Vozac> vozaci = Vozac.vozaciBezAutomobila();
        if (!vozaci.isEmpty()) {
            for (Vozac vozac : vozaci) {
                if (vozac.getAutomobil() == null) {
                    System.out.println(vozac.toString());
                }
            }
            System.out.println("Izaberite JMBG vozaca kojem dodeljujete automobil: ");
            String JMBG = sc.nextLine();
            Vozac vozac;
            if ((vozac = Vozac.pronadjiPoJMBG(Long.parseLong(JMBG))) != null) {
                automobil.setVozac(vozac.getJMBG());
                System.out.println("Uspesno ste dodali vozaca");
            }
        } else {
            automobil.setVozac(null);
            System.out.println("Vozac ne postoji.");
        }
    }

    // 2.2.5
    public static void dodeljivanjeVoznjiKreiranihTelefonom() {
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        for (Voznja voznja : voznje) {
            if (voznja.getNacinPorudzbine().equals("TELEFONOM")) {
                System.out.println(voznja);
            }
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Unesi id vožnje");
        long idVoznje = scanner.nextLong();

        Voznja voznja = null;
        for (Voznja voznja2 : voznje) {
            if (voznja2.getId() == idVoznje) {
                voznja = voznja2;
            }
        }

        System.out.println("Unesi jmbg vozača: ");
        long jmbg = scanner.nextLong();
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();

        Vozac vozac = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == jmbg) {
                vozac = (Vozac) korisnik;
            }
        }
        System.out.println(vozac);

        voznja.setVozac(vozac);
        voznja.setStatusVoznje("DODELJENA");
        Voznja.upisiVoznje(voznje);

        System.out.println("Uspešno dodeljena vožnja");

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

}