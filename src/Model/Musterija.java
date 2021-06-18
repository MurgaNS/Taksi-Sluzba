package Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Musterija extends Korisnik {

    public Musterija(long JMBG, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, Boolean obrisan) {
        super(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, obrisan);
    }

    public static void narucivanjePutemTelefona() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adresa polaska: ");
        String adresaPolaska = scanner.nextLine();
        System.out.println("Adresa destinacije: ");
        String adresaDestinacije = scanner.nextLine();
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        long idNoveVoznje = voznje.get(voznje.size() - 1).getId() + 1;
        Voznja voznja = new Voznja(idNoveVoznje, new Date(), adresaPolaska, adresaDestinacije, 0, 0, Voznja.StatusVoznje.KREIRANA, Voznja.NacinPorudzbine.TELEFONOM, Vozac.prijavljeniKorisnik.getJMBG(), (long) -1, 0);
        voznje.add(voznja);
        Voznja.upisiVoznje(voznje);
        System.out.println("Vožnja putem telefona je uspešno naručena!");
    }

    public static void narucivanjePutemAplikacije() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adresa polaska: ");
        String adresaPolaska = scanner.nextLine();
        System.out.println("Adresa destinacije: ");
        String adresaDestinacije = scanner.nextLine();
        System.out.println("Napomena: ");
        String napomena = scanner.nextLine();
        List<Voznja> voznje = Voznja.ucitajSveVoznje();
        long idNoveVoznje = voznje.get(voznje.size() - 1).getId() + 1;
        Voznja voznja = new Voznja(idNoveVoznje, new Date(), adresaPolaska, adresaDestinacije, 0, 0, Voznja.StatusVoznje.KREIRANA_NA_CEKANJU, Voznja.NacinPorudzbine.APLIKACIJOM, Vozac.prijavljeniKorisnik.getJMBG(), (long) -1, 0);
        voznje.add(voznja);
        Voznja.upisiVoznje(voznje);
        System.out.println("Voznja putem aplikacije je uspesno narucena.");

    }

    public static void prikazIstorijeVoznji() {
    }

    public static void prikaziMusterije() {
        List<Korisnik> sviKorisnici = Korisnik.ucitajSveKorisnike();
        for (Korisnik korisnik : sviKorisnici) {
            if (korisnik instanceof Musterija && !korisnik.isObrisan()) {
                System.out.println(korisnik);
            }
        }
    }

    @Override
    public String toString() {
        return "Musterija{" +
               "JMBG=" + JMBG +
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

    public static void izmeniMusteriju() {
        System.out.println("Unesi JMBG musterije koju zelite da izmenite");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Musterija musterija = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == JMBG) {
                musterija = (Musterija) korisnik;
            }
        }
        if (musterija == null) {
            return;
        }
        System.out.println("1. Izmena korisnickog imena" +
                           "\n2. Izmena sifre" +
                           "\n3. Izmena imena" +
                           "\n4. Izmena prezimena" +
                           "\n5. Izmena adrese" +
                           "\n6. Izmena pola" +
                           "\n7. Izmena broja telefona");
        System.out.println("Odaberi opciju");
        int opcija = scanner.nextInt();
        switch (opcija) {
            case 1 -> {
                System.out.println("Unesi korisnicko ime: ");
                String korisnickoIme = scanner.next();
                musterija.setKorisnickoIme(korisnickoIme);
            }
            case 2 -> {
                System.out.println("Unesi lozinku: ");
                String lozinka = scanner.next();
                musterija.setLozinka(lozinka);
            }
            case 3 -> {
                System.out.println("Unesi ime: ");
                String ime = scanner.next();
                musterija.setIme(ime);
            }
            case 4 -> {
                System.out.println("Unesi prezime: ");
                String prezime = scanner.next();
                musterija.setPrezime(prezime);
            }
            case 5 -> {
                System.out.println("Unesite adresu: ");
                String adresa = scanner.next();
                musterija.setAdresa(adresa);
            }
            case 6 -> {
                System.out.println("Unesite pol [MUSKI/ZENSKI]: ");
                String pol = scanner.next();
                if (pol.trim().equals("MUSKI")) {
                    musterija.setPol(Pol.MUSKI);
                } else {
                    musterija.setPol(Pol.ZENSKI);
                }
            }
            case 7 -> {
                System.out.println("Unesite broj telefona: ");
                String brojTelefona = scanner.nextLine();
                musterija.setBrojTelefona(brojTelefona);
            }
        }
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno izmenjen korisnik");
    }

    public static void izbrisiMusteriju() {
        System.out.println("Unesi JMBG musterije koju zelite da obrisete");
        Scanner scanner = new Scanner(System.in);
        long JMBG = scanner.nextLong();
        List<Korisnik> korisnici = ucitajSveKorisnike();
        Musterija musterija = null;
        for (Korisnik korisnik : korisnici) {
            if (korisnik.getJMBG() == JMBG) {
                musterija = (Musterija) korisnik;
            }
        }
        if (musterija == null) {
            return;
        }
        musterija.setObrisan(true);
        Korisnik.upisiSveKorisnike(korisnici);
        System.out.println("Uspešno ste obrisali musteriju!");
    }

    public static void dodajMusteriju() {
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
        Musterija musterija = new Musterija(JMBG, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona, false);
        List<Korisnik> korisnici = Korisnik.ucitajSveKorisnike();
        korisnici.add(musterija);
        Korisnik.upisiSveKorisnike(korisnici);
    }

    public static void prikaziMeni() {
        System.out.println("1. Narucivanje voznje putem telefona");
        System.out.println("2. Narucivanje voznje putem aplikacije");
        System.out.println("3. Prikaz svih voznji");
    }

    public ArrayList<Voznja> ucitajListuVoznji(Musterija musterija) throws IOException, ParseException {
        ArrayList<Voznja> listaVoznji = new ArrayList<Voznja>();
        BufferedReader bf = new BufferedReader(new FileReader("src/Data/voznje.csv"));
        String red;
        while ((red = bf.readLine()) != null) {
            String[] tmp = red.split(",");
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date = (Date) formatter.parse(tmp[1]);
            Vozac vozac = Vozac.pronadjiPoJMBG(Long.parseLong(tmp[8]));
            Voznja.NacinPorudzbine nacinPorudzbine;
            Voznja.StatusVoznje statusVoznje;
            statusVoznje = Voznja.ucitajStatusVoznje(tmp[6]);
            nacinPorudzbine = Voznja.ucitajNacinPorudzbine(tmp[7]);
            Voznja voznja = new Voznja(Long.parseLong(tmp[0]), date, tmp[2], tmp[3], Double.parseDouble(tmp[4]), Double.parseDouble(tmp[5]), statusVoznje, nacinPorudzbine, null, null, Double.parseDouble(tmp[10]));
        }
        return listaVoznji;
    }


    public String korisnikUString() {
        return "musterija," + super.korisnikUString();
    }
}