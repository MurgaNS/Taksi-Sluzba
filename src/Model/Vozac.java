package Model;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Vozac extends Korisnik {


    public static Korisnik prijavljeniKorisnik = null;
    private double plata;
    private int brojClanskeKarte;
    private List<Voznja> listaVoznji;
    private Long regOznakaVozila;


    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, double plata, int brojClanskeKarte, List<Voznja> listaVoznji, Long regOznakaVozila) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
        this.listaVoznji = listaVoznji;
        this.regOznakaVozila = regOznakaVozila;
    }

    public Vozac(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, double plata, int brojClanskeKarte) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona);
        this.plata = plata;
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public String korisnikUString() {
        return "vozac," + super.korisnikUString() + "," + plata + "," + brojClanskeKarte;
    }

    public static void prikaziMeni() {
        System.out.println("1. Prikaz istorije sopstvenih voznji");
        System.out.println("2. Prikaz svih voznji zakazanih putem aplikacije");
        System.out.println("3. Prikaz dodeljenih voznji");
        System.out.println("4. Statistika");
        System.out.println("5. Aukcija");
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
        System.out.println("Unesi JMBG: ");
        long JMBG = Long.parseLong(scanner.nextLine());
        System.out.println("Unesi korisnicko ime: ");
        String korisnickoIme = scanner.nextLine();
        System.out.println("Unesi lozinku: ");
        String lozinka = scanner.nextLine();
        System.out.println("Unesi ime: ");
        String ime = scanner.nextLine();
        System.out.println("Unesi prezime: ");
        String prezime = scanner.nextLine();
        System.out.println("Unesi adresu: ");
        String adresa = scanner.nextLine();
        System.out.println("Unesi pol: ");
        String polTxt = scanner.nextLine();
        Pol pol;
        if (polTxt.trim().equals("MUSKI")) {
            pol = Pol.MUSKI;
        } else {
            pol = Pol.ZENSKI;
        }
        System.out.println("Broj telefona");
        String brojTelefona = scanner.nextLine();
        System.out.println("Unesi platu");
        double plata = scanner.nextDouble();
        System.out.println("Unesi broj članske karte");
        int brojClanskeKarte = scanner.nextInt();

        Vozilo.ispisiSvaSlobodnaVozila();
        System.out.println("Odaberi vozilo(unesi broj registarske oznake): ");

        String brRegistarskeOznake = scanner.next();

        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
        for (Vozilo vozilo : automobili) {
            if (vozilo.getBrRegistarskeOznake().equals(brRegistarskeOznake)) {
                vozilo.setVozacId(JMBG);
                break;
            }
        }
        Vozilo.sacuvajListuAutomobilaUFajl(automobili);

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
            case 1 -> {
                System.out.println("Unesi korisnicko ime: ");
                String korisnickoIme = scanner.next();
                vozac.setKorisnickoIme(korisnickoIme);
            }
            case 2 -> {
                System.out.println("Unesi lozinku: ");
                String lozinka = scanner.next();
                vozac.setLozinka(lozinka);
            }
            case 3 -> {
                System.out.println("Unesi ime: ");
                String ime = scanner.next();
                vozac.setIme(ime);
            }
            case 4 -> {
                System.out.println("Unesi prezime: ");
                String prezime = scanner.next();
                vozac.setPrezime(prezime);
            }
            case 5 -> {
                System.out.println("Unesite adresu: ");
                String adresa = scanner.next();
                vozac.setAdresa(adresa);
            }
            case 6 -> {
                System.out.println("Unesite pol [MUSKI/ZENSKI]: ");
                String pol = scanner.next();
                if (pol.trim().equals("MUSKI")) {
                    vozac.setPol(Pol.MUSKI);
                } else {
                    vozac.setPol(Pol.ZENSKI);
                }
            }
            case 7 -> {
                System.out.println("Unesite broj telefona: ");
                String brojTelefona = scanner.nextLine();
                vozac.setBrojTelefona(brojTelefona);
            }
            case 8 -> {
                System.out.println("Unesite platu: ");
                double plata = scanner.nextDouble();
                vozac.setPlata(plata);
            }
            case 9 -> {
                System.out.println("Unesite broj clanske karte");
                int brojClanskeKarte = scanner.nextInt();
                vozac.setBrojClanskeKarte(brojClanskeKarte);
            }
        }


        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno upisan korisnik");
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
        List<Korisnik> sviKorisnici = Korisnik.ucitajSveKorisnike();

        for (Korisnik vozac : sviKorisnici) {
            if (vozac instanceof Vozac) {
                if (vozac.getIme().equalsIgnoreCase(ime) && vozac.getPrezime().equalsIgnoreCase(prezime)
                    && ((Vozac) vozac).getPlata() >= minPlata && ((Vozac) vozac).getPlata() <= maxPlata) {
                    String regOznaka = String.valueOf(((Vozac) vozac).getRegOznakaVozila());
                    Vozilo vozilo = Vozilo.pronadjiPoBrojuRegistarskeOznake(regOznaka);
                    System.out.println(vozac);

                }
            }
        }
    }

    public static void prikazDodeljenihVoznji() {

        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        for (Voznja voznja : voznje) {
            if (voznja.getNacinPorudzbine() == Voznja.NacinPorudzbine.TELEFONOM && voznja.getStatusVoznje() == Voznja.StatusVoznje.DODELJENA) {
                long vozacJMBG = voznja.getVozacId();
                if (prijavljeniKorisnik.getJMBG() == vozacJMBG) {
                    System.out.println(voznja);
                }


            }
        }
        Scanner skener = new Scanner(System.in);
        System.out.println("Unesi id vožnje");
        long idVoznje = skener.nextLong();
        Voznja voznjaIzmena = null;
        for(Voznja voznja : voznje) {
            if(voznja.getId() == idVoznje){
                voznjaIzmena = voznja;
                break;
            }
        }
        if(voznjaIzmena == null){
            System.out.println("Nema vožnje sa tim id-em");
            return;
        }
        System.out.println("Da li želite da prihvatite ili odbijete vožnju(Y/N)?");
        String izbor = skener.next();
        if(izbor.equals("Y")){
            voznjaIzmena.setStatusVoznje(Voznja.StatusVoznje.PRIHVACENA);
            System.out.println("Uspešno prihvaćena vožnja!");
        }
        else if(izbor.equals("N")){
            voznjaIzmena.setStatusVoznje(Voznja.StatusVoznje.ODBIJENA);
            System.out.println("Uspešno odbijena vožnja!");
        }
        else{
            System.out.println("Nepostojeca opcija.");
        }
        Voznja.upisiVoznje(voznje);
    }

    // 2.3.4 - završavanje vožnje funkcionalnost  @jovan
    public static void zavrsavanjeVoznje(){
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        Vozac vozac = (Vozac)Vozac.prijavljeniKorisnik;
        Voznja voznjaZavrsavanje = null;
        for(Voznja voznja : voznje){
            if(voznja.getVozacId() == vozac.getJMBG() &&  voznja.getStatusVoznje() == Voznja.StatusVoznje.PRIHVACENA) {
                voznjaZavrsavanje = voznja;
                break;
            }
        }
        if(voznjaZavrsavanje == null){
            System.out.println("Nemate vožnji za završavanje.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Unesi trajanje vožnje:");
        double trajanje = scanner.nextDouble();
        System.out.println("Unesi broj pređenih km:");
        double brojKm = scanner.nextDouble();
        voznjaZavrsavanje.setTrajanjeVoznjeUMinutama(trajanje);
        voznjaZavrsavanje.setBrojPredjenihKilometara(brojKm);
        Voznja.upisiVoznje(voznje);
        System.out.println("Uspešno završena vožnja!");
    }



    public void prikazIstorijeSpostvenihVoznji(Vozac vozac) {
        for (Voznja voznja : vozac.getListaVoznji()) {
            System.out.println(voznja.toString());
        }
    }

    public static void prikazVoznjiPutemAplikacije() {
        List<Voznja> listaVoznji = Voznja.ucitajSveVoznje();
//        todo prihvatanje/odbijanje voznje
        for (Voznja voznja : listaVoznji) {
            if (voznja.getNacinPorudzbine() == Voznja.NacinPorudzbine.APLIKACIJOM) {
                System.out.println(voznja);
            }
        }
    }

    public void prikazVoznjiPutemTelefona() {
    }

    public boolean krajVoznje(double predjeniKilometri, double trajanjeVoznje) {
        return false;
    }

    public void dnevniIzvestaj() {
    }

    public void nedeljniIzvestaj() {
    }

    public void mesecniIzvestaj() {
    }

    public void godisnjiIzvestaj() {
    }

    public void aukcija(int minutaDoDolaska) {
    }

    public static Vozac pronadjiPoJMBG(long JMBG) {
        List<Vozac> vozaci = ucitajSveVozace();
        for (Vozac vozac : vozaci) {
            if (vozac.getJMBG() == JMBG) {
                return vozac;
            }
        }
        return null;
    }

    public static List<Vozac> nadjiVozaceZaProizvodjaca(String proizvodjac) {
        List<Vozac> vozaci = new ArrayList<>();
        List<Vozilo> automobili = Vozilo.ucitajSveAutomobile();
        for (Vozilo vozilo : automobili) {
            if (vozilo.getVozacId() != null && vozilo.getProizvodjac().equals(proizvodjac)) {
                long jmbg = vozilo.getVozacId();
                Vozac vozac = pronadjiPoJMBG(jmbg);
                if (vozac != null) {
                    vozaci.add(vozac);
                }

            }
        }
        return vozaci;
    }

    public static void nedeljniIzvestaj(Vozac vozac) {
        List<Voznja> listaVoznji = voznjePrethodnihNedeljuDana(vozac);
        for (Voznja voznja : listaVoznji
        ) {
            System.out.println(voznja.toString());
        }
    }

    public static List<Voznja> voznjePrethodnihNedeljuDana(Vozac vozac) {
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
            if (vozac.getRegOznakaVozila() == null) {
                retVal.add(vozac);
            }
        }
        return retVal;
    }

    public static ArrayList<Vozac> ucitajSveVozace() {
        // TODO dodati listu voznji
        ArrayList<Vozac> vozaci = new ArrayList<>();
        List<Vozilo> listaVozila = Vozilo.ucitajSveAutomobile();

        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/korisnici.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Pol pol;
                if (tmp[7].trim().equals("MUSKI")) {
                    pol = Pol.MUSKI;
                } else {
                    pol = Pol.ZENSKI;
                }
                if (tmp[0].equals("vozac")) {
                    Vozilo vozilo = null;
                    try {
                        if (Vozilo.pronadjiPoBrojuTaksiVozila(tmp[11], listaVozila) != null) {
                            vozilo = Vozilo.pronadjiPoBrojuTaksiVozila(tmp[11], listaVozila);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        break;
                    }
                    Vozac vozac = new Vozac(Long.parseLong(tmp[1]), tmp[2], tmp[3], tmp[4], tmp[5], tmp[6], pol, tmp[8], Double.parseDouble(tmp[9]), Integer.parseInt(tmp[10]), null, Long.parseLong(vozilo.getBrTaksiVozila()));
                    vozac.setListaVoznji(ucitajListuVoznji(vozac));
                    vozaci.add(vozac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    public static List<Voznja> ucitajListuVoznji(Vozac vozac) {
        List<Voznja> listaVoznji = new ArrayList<>();
        String red;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/Data/voznje.csv"));
            while ((red = bf.readLine()) != null) {
                String[] tmp = red.split(",");
                Voznja.NacinPorudzbine nacinPorudzbine;
                nacinPorudzbine = Voznja.ucitajNacinPorudzbine(tmp[7]);
                Voznja.StatusVoznje statusVoznje;
                statusVoznje = Voznja.ucitajStatusVoznje(tmp[6]);
                if (tmp[8].equals(String.valueOf(vozac.getJMBG()))) {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date date = (Date) formatter.parse(tmp[1]);

                    Voznja voznja = new Voznja(Long.parseLong(tmp[0]), date, tmp[2], tmp[3], Double.parseDouble(tmp[4]), Double.parseDouble(tmp[5]), statusVoznje, nacinPorudzbine, Long.parseLong(tmp[8]), Long.parseLong(tmp[9]));
                    listaVoznji.add(voznja);
                } else {
                    System.out.println("Ne postoje voznje za ovog korisnika.");
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaVoznji;
    }

    public static void prikaziListuVoznji(List<Voznja> listaVoznji) {
        for (Voznja voznja : listaVoznji) {
            System.out.println(voznja.toString());
        }
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
               ", automobil=" + regOznakaVozila +
               '}';
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

    public Long getRegOznakaVozila() {
        return regOznakaVozila;
    }

    public void setRegOznakaVozila(Long regOznakaVozila) {
        this.regOznakaVozila = regOznakaVozila;
    }

}